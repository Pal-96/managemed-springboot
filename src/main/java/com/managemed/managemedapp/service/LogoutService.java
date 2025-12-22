package com.managemed.managemedapp.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    public void logout(HttpServletResponse response) {
        Cookie tokenCookie = new Cookie("token", "");
        tokenCookie.setMaxAge(0);
        // tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
    }
}
