package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.AddCartService;
import com.managemed.managemedapp.util.CookieUtil;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class AddCartController {

    @Autowired
    AddCartService addCartService;

    // public AddCartController(AddCartService addCartService) {
    //     this.addCartService = addCartService;
    // }

    @PostMapping("/addtocart")
    public String handleAddToCart(
        @RequestParam String product,
        @RequestParam String cartquan,
        @RequestParam(required = false) String addtocart,
        @RequestParam(required = false) String removecart,
        HttpServletRequest request,
        HttpSession session)
        {
        String token = CookieUtil.getToken(request);
        String username = JWTUtil.getUsername(token);
        if (addtocart != null && !cartquan.isEmpty()) {
            boolean success = addCartService.handleAddToCart(product, cartquan, addtocart, username, session);
            return "redirect:/displayall";
        }

        return "redirect:/displayall";
    }

    @PostMapping("/removecart")
    public String handleRemoveFromCart(
        @RequestParam String product,
        HttpServletRequest request,
        HttpSession session) {
        String token = CookieUtil.getToken(request);
        String username = JWTUtil.getUsername(token);
        addCartService.handleRemoveFromCart(product, username, session);
        return "redirect:/viewcart";
    }

}
