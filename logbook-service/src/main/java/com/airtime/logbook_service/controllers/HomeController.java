package com.airtime.logbook_service.controllers;

import com.airtime.logbook_service.persistence.model.Person;
import com.airtime.logbook_service.persistence.model.User;
import com.airtime.logbook_service.service.PersonService;
import com.airtime.logbook_service.service.UserService;
import com.airtime.logbook_service.web.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.airtime.logbook_service.service.MailService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    private String appName;

    private final MailService mailService;
    private final PersonService personService;
    private final UserService userService;

    public HomeController(MailService mailService, PersonService personService, UserService userService) {
        this.mailService = mailService;
        this.personService = personService;
        this.userService = userService;
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

    @GetMapping("/test")
    public String testAuth(Model model, OAuth2AuthenticationToken authentication) {
        System.out.println(authentication);
        //System.out.println(authentication.getPrincipal().getAttributes());
        String appName = this.appName;
        model.addAttribute("appName", appName);
        model.addAttribute("message", "Hello, World!");
        /*if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }*/
        return "home";
    }

    @GetMapping("/mail")
    public String Mail(Model model) {
        String appName = this.appName;
        model.addAttribute("appName", appName);
        //mailService.sendDailyUpdate();
        return "home";
    }
}
