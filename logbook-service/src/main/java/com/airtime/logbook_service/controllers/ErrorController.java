package com.airtime.logbook_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/403")
    public String forbidden(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("message", "403");
        return "error";
    }

    @GetMapping("/404")
    public String notFound(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("message", "404");
        return "error";
    }

    @GetMapping("/500")
    public String internal(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("message", "500");
        return "error";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("message", "Access denied");
        return "error";
    }
}
