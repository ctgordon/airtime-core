package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.persistence.model.User;
import com.airtime.logbook_service.persistence.model.UserProfile;
import com.airtime.logbook_service.service.ProfileService;
import com.airtime.logbook_service.service.UserProfileService;
import com.airtime.logbook_service.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.airtime.logbook_service.service.MailService;

import java.util.List;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    private String appName;

    private final MailService mailService;
    private final ProfileService profileService;
    private final UserService userService;
    private final UserProfileService userProfileService;

    public HomeController(
            MailService mailService,
            ProfileService profileService,
            UserService userService,
            UserProfileService userProfileService) {
        this.mailService = mailService;
        this.profileService = profileService;
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/")
    public String home(Model model) {
        String appName = this.appName;
        model.addAttribute("appName", appName);
        model.addAttribute("message", "Hello, World!");
        return "home";
    }

    @GetMapping("/test-email")
    public String testEmail(Model model) {
        String appName = this.appName;
        model.addAttribute("appName", appName);
        model.addAttribute("message", "Hello, World!");
        return mailService.sendTestEmail() ? "home" : "error";
    }

    @GetMapping("/mail")
    public String Mail(Model model) {
        String appName = this.appName;
        model.addAttribute("appName", appName);
        //mailService.sendDailyUpdate();
        return "home";
    }
}
