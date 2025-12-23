package com.managemed.managemedapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.managemed.managemedapp.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    // public LoginController(LoginService loginService) {
    //     this.loginService = loginService;
    // }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response,
            HttpSession session) {

        LoginService.LoginResult result =
                loginService.authenticate(username, password);

        if (result.isSuccess()) {

            Cookie tokenCookie = new Cookie("token", result.getToken());
            tokenCookie.setHttpOnly(true);
            tokenCookie.setMaxAge(1800); // 30 mins
            tokenCookie.setPath("/");

            response.addCookie(tokenCookie);
            response.setHeader("Authorization", "Bearer " + result.getToken());

            return "redirect:/home";

        } else {
            session.setAttribute("invalidcreds", true);
            return "redirect:/login-page";
        }
    }
}
