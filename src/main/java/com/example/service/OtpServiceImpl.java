package com.example.service;


import com.example.model.User;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpServiceImpl implements OtpService {

    private LoadingCache<String, Integer> otpCache;

    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public OtpServiceImpl(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
        configureOTPCache();
    }

    private void configureOTPCache() {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS) // cache will expire after 60 seconds
                .maximumSize(100) // max cache size
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String username) {
                        return 0;
                    }
                });
    }

    @Override
    public int generateOTP(String username) {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Here the logic is - minimum length of otp is 4 digit and maximum length is 4 digit, 1000 < range < 10000
        otpCache.put(username, otp);
        return otp;
    }

    @Override
    public Optional<Integer> getOTP(String username) {
        try {
            return Optional.ofNullable(otpCache.get(username));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void clearOTP(String username) {
        otpCache.invalidate(username);
    }

    @Override
    public void generateAndSendOTP(String emailId) {
        User user = this.userService.findByEmail(emailId);
        if (user != null) {
            // if exist, clear already existed otp
            clearOTP(user.getUsername());

            // generate otp
            int otp = generateOTP(user.getUsername());

            // send otp email
            this.emailService.sendOTPEmail(user, otp);
        }
    }

    @Override
    public boolean isValidOTP(String emailId, String otp) {
        if (StringUtils.isNotBlank(emailId) && StringUtils.isNotBlank(otp) && StringUtils.isNumeric(otp)) {
            User user = this.userService.findByEmail(emailId);
            if (user != null) {
                Optional<Integer> actualOTP = getOTP(user.getUsername());
                if (actualOTP.isPresent()) {
                    return actualOTP.get().equals(Integer.valueOf(otp));
                }
            }
        }
        return false;
    }
}
