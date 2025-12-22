package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.managemed.managemedapp.service.LogoutService;

@Controller
public class LogoutController {

    private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        logoutService.logout(response);
        return "redirect:/login-page";
    }
}
