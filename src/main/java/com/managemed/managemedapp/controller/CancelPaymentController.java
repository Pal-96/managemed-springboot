package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.RestoreStockService;
import com.managemed.managemedapp.util.CookieUtil;

@Controller
public class CancelPaymentController {
    @Autowired
    RestoreStockService cancelPaymentService;

    @GetMapping("/cancel")
    public String handleGoBack(HttpServletRequest request) {

        String token = CookieUtil.getToken(request);
        String username = JWTUtil.getUsername(token);
        cancelPaymentService.cancelPayment(username);
        return "cancel";
    }
}
