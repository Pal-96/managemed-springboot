package com.managemed.managemedapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @GetMapping("/login-page")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/manageusers")
    public String manageUsers() {
        return "ManageUsers";
    }
}