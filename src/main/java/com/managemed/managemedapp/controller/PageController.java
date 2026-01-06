package com.managemed.managemedapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "OK";
    }

    // @GetMapping("/welcome")
    // public String welcomePage() {
    //     return "Welcome";
    // }

    @GetMapping("/login-page")
    public String loginPage() {
        return "Login";
    }

    // @GetMapping("/home")
    // public String homePage() {
    //     return "Home";
    // }

    // @GetMapping("/displayall")
    // public String DisplayAll() {
    //     return "DisplayAll";
    // }

    // @GetMapping("/viewcart")
    // public String viewCart() {
    //     return "ViewCart";
    // }

    @GetMapping("/myorders")
    public String myOrders() {
        return "MyOrders";
    }

    @GetMapping("/manageusers")
    public String manageUsers() {
        return "ManageUsers";
    }

    @GetMapping("/manageroles")
    public String manageRoles() {
        return "ManageRoles";
    }

    // @GetMapping("/success")
    // public String paymentSuccess() {
    //     return "success";
    // }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "cancel";
    }

    @GetMapping("/paymenterror")
    public String paymentError() {
        return "PaymentError";
    }
}