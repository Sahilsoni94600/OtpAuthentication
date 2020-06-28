package com.example.service;

import java.util.Optional;

public interface OtpService {

    int generateOTP(String username);

    Optional<Integer> getOTP(String username);

    void clearOTP(String username);

    void generateAndSendOTP(String emailId);

    boolean isValidOTP(String emailId, String otp);
}
