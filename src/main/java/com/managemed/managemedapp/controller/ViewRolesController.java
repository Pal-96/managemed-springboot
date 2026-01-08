package com.managemed.managemedapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.managemed.managemedapp.model.Roles;
import com.managemed.managemedapp.security.JWTUtil;
import com.managemed.managemedapp.service.ViewRolesService;
import com.managemed.managemedapp.util.CookieUtil;


@Controller
public class ViewRolesController {

    @Autowired
    ViewRolesService ViewRolesService;
    @GetMapping("/manageroles")
    public String manageRoles(HttpServletRequest request, Model model) {
        String token = CookieUtil.getToken(request);
        if (token == null) {
            return "redirect:/login-page";
        }

        List<Roles> roles = ViewRolesService.viewRoles();
        model.addAttribute("roles", roles);
        return "ManageRoles";
    }
    
}
