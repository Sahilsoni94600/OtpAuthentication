package com.example.controller;

import com.example.model.User;
import com.example.service.OtpService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final OtpService otpService;
    private final UserService userService;

    @Autowired
    public LoginController(OtpService otpService, UserService userService) {
        this.otpService = otpService;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/generateOtp", method = RequestMethod.POST)
    public String generateOtp(@RequestParam("emailId") String emailId, ModelMap modelMap) {
        otpService.generateAndSendOTP(emailId);
        modelMap.put("emailId", emailId);

        return "loginOtp";
    }

    @RequestMapping(value = "/otpVerification", method = RequestMethod.POST)
    public String otpVerification(@RequestParam("emailId") String emailId, @RequestParam("otp") String otp, RedirectAttributes redirectAttrs) {
        if (otpService.isValidOTP(emailId, otp)) {
            User user = this.userService.findByEmail(emailId);
            redirectAttrs.addAttribute("username", user.getUsername());
            return "redirect:welcome";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(@RequestParam("username") String username, ModelMap modelMap) {
        User user = this.userService.findByUsername(username);
        modelMap.put("username", user.getUsername());
        modelMap.put("emailId", user.getEmail());

        return "welcome";
    }
}
