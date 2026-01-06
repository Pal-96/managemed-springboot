package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.PaymentSuccessService;
import com.managemed.managemedapp.util.CookieUtil;


@Controller
public class PaymentSuccessController {

    @Autowired
    PaymentSuccessService paymentSuccessService;

    @GetMapping("/success")
    public String handlePaymentSuccess(HttpServletRequest request) {
        // Implementation for handling payment success
        String token = CookieUtil.getToken(request);
        String username = JWTUtil.getUsername(token);
        // model.addAttribute("username", username);
        paymentSuccessService.proceedSale(username);
        return "success";
    }
    
}
