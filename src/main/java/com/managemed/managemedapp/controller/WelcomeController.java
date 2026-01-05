package com.managemed.managemedapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.managemed.managemedapp.dto.DashboardStats;
import com.managemed.managemedapp.dto.EmailConfig;
import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.DashboardService;
import com.managemed.managemedapp.util.CookieUtil;

@Controller
public class WelcomeController {
   private final DashboardService dashboardService;
    private final EmailConfig emailConfig;

    public WelcomeController(DashboardService dashboardService,
                          EmailConfig emailConfig) {
        this.dashboardService = dashboardService;
        this.emailConfig = emailConfig;
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request, Model model) {

        String token = CookieUtil.getToken(request);

        boolean loggedIn = token != null;
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("emailPublicKey", emailConfig.getPublicKey());

        if (loggedIn) {
            String username = JWTUtil.getUsername(token);
            dashboardService.getFullName(username)
                    .ifPresent(name -> model.addAttribute("name", name));
        }

        DashboardStats stats = dashboardService.getStats();
        model.addAttribute("customers", stats.getCustomers());
        model.addAttribute("totalSales", stats.getTotalSales());
        model.addAttribute("products", stats.getProducts());

        return "Welcome";
    } 
}
