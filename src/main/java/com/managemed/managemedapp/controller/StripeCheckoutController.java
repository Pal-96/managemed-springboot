package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.StripeCheckoutService;
import com.managemed.managemedapp.util.CookieUtil;
import com.stripe.Stripe;

@Controller
public class StripeCheckoutController {

    private final StripeCheckoutService checkoutService;

    @Value("${stripe.sk}")
    private String stripeSecretKey;

    @Value("${domain.url}")
    private String domainUrl;

    public StripeCheckoutController(StripeCheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(
            @RequestParam("shipping") int shippingPrice,
            HttpServletRequest request) {

        try {
            // Set Stripe key once per request
            Stripe.apiKey = stripeSecretKey;

            String token = CookieUtil.getToken(request);
            String username = JWTUtil.getUsername(token);

            String checkoutUrl =
                    checkoutService.createCheckoutSession(
                            username,
                            shippingPrice,
                            domainUrl);

            return "redirect:" + checkoutUrl;

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Stripe checkout session", e);
        }
    }
}
