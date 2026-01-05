package com.managemed.managemedapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.managemed.managemedapp.model.*;
import com.managemed.managemedapp.service.ProductService;
import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.util.CookieUtil;


@Controller
public class DisplayAllController {

    @Autowired
    private ProductService productService;

    @GetMapping("/displayall")
    public String displayAll(HttpServletRequest request, Model model) {

        String token = CookieUtil.getToken(request);
        if (token == null) {
            return "redirect:/login-page";
        }

        String role = JWTUtil.getRole(token);
        String product = request.getParameter("product");

        List<Product> products = productService.getProducts(product);

        model.addAttribute("products", products);
        model.addAttribute("role", role);

        return "DisplayAll";
    }
}

