package com.managemed.managemedapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.managemed.managemedapp.dto.OrderItemDTO;
import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.OrderService;
import com.managemed.managemedapp.util.CookieUtil;

@Controller
public class ViewOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/myorders")
    public String myOrders(HttpServletRequest request, Model model) {

        String token = CookieUtil.getToken(request);
        if (token == null) {
            return "redirect:/login-page";
        }

        String username = JWTUtil.getUsername(token);
        List<OrderItemDTO> orders = orderService.getUserOrders(username);

        model.addAttribute("orders", orders);
        return "MyOrders";
    }
}
