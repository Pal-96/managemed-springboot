package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.util.CookieUtil;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {

        String token = CookieUtil.getToken(request);
        if (token == null) {
            return "redirect:/login-page";
        }

        String role = JWTUtil.getRole(token);
        String username = JWTUtil.getUsername(token);
        boolean loggedIn = token != null;
        model.addAttribute("username", username);
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("role", role);

        return "Home";
    }
}
