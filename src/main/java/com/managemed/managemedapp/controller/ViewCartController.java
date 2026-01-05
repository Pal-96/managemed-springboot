package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.ViewCartService;
import com.managemed.managemedapp.util.CookieUtil;

@Controller
public class ViewCartController {

    @Autowired
    private ViewCartService viewCartService;

    @GetMapping("/viewcart")
    public String viewCart(HttpServletRequest request, Model model) {

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
        model.addAttribute("rowCount", viewCartService.getCartCount(username));
        model.addAttribute("cartItems", viewCartService.getAllCartItems(username));

        double shipping = 0.0;
        double totalAmount = viewCartService.getTotalAmount(username);
        if (totalAmount > 0) {
            shipping = 50.0;
        }
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("shipping", shipping);

        return "ViewCart";
    }
}
