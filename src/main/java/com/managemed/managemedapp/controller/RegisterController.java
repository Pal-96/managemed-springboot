package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.managemed.managemedapp.service.RegisterService;

@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;

    // public RegisterController(RegisterService registerService) {
    //     this.registerService = registerService;
    // }

    @GetMapping("/register-page")
    public String showRegisterPage() {
        return "Register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam("action") String action,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String selectedRole,
            HttpSession session) {

        switch (action) {

            case "register":
                boolean registered = registerService.registerCustomer(
                        firstname, lastname, username, password);

                if (registered) {
                    return "redirect:/login-page";
                } else {
                    session.setAttribute("signuperror", true);
                    return "redirect:/register-page";
                }

            case "edit":
                registerService.editUser(firstname, lastname, username, selectedRole);
                return "redirect:/manageusers";

            case "add":
                boolean added = registerService.addUser(
                        firstname, lastname, username, password, selectedRole);

                if (added) {
                    return "redirect:/manageusers";
                } else {
                    session.setAttribute("signuperror", true);
                    return "redirect:/register-page";
                }

            case "delete":
                registerService.deleteUser(username);
                return "redirect:/manageusers";

            default:
                return "redirect:/register-page";
        }
    }
}
