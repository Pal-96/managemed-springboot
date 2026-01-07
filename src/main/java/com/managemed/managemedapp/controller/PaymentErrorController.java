package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.RestoreStockService;
import com.managemed.managemedapp.util.CookieUtil;

@Controller
public class PaymentErrorController {

    @Autowired
    RestoreStockService restoreStockService;
    
    @GetMapping("/paymenterror")
    public String paymentError(HttpServletRequest request) {
        String token = CookieUtil.getToken(request);
        String username = JWTUtil.getUsername(token);
        restoreStockService.restoreStock(username);

        return "PaymentError";
    }
}
