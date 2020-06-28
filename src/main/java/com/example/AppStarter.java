package com.example;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.utils.PasswordUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AppStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppStarter.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }

    @Bean
    public CommandLineRunner addDummyData(UserDao userDao) {
        return args -> {
            createOrUpdateUser("SahilSoni946", "sahilsoni94600@gmail.com", "1235", "1234", userDao);
            createOrUpdateUser("test2", "test2@gmail.com", "1234", "4567", userDao);
            createOrUpdateUser("test3", "test3@gmail.com", "1232", "5678", userDao);
            createOrUpdateUser("test4", "test4@gmail.com", "1238", "6789", userDao);
        };
    }

    private void createOrUpdateUser(String username, String emailId, String contactNumber, String password, UserDao userDao) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setEmail(emailId);
            user.setUsername(username);
            user.setContactNumber(contactNumber);
            user.setPassword(PasswordUtils.encryptPassword(password));
            userDao.save(user);
        }
    }
}