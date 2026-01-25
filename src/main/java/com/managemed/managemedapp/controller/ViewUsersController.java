package com.managemed.managemedapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.managemed.managemedapp.model.Roles;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.service.ViewUsersService;
import com.managemed.managemedapp.service.ViewRolesService;
import com.managemed.managemedapp.util.CookieUtil;


@Controller
public class ViewUsersController {

    @Autowired
    ViewUsersService ViewUsersService;

    @Autowired
    ViewRolesService ViewRolesService;

    @GetMapping("/manageusers")
    public String manageUsers(HttpServletRequest request, Model model) {
        String token = CookieUtil.getToken(request);
        if (token == null) {
            return "redirect:/login-page";
        }

        List<User> users = ViewUsersService.viewUsers();
        model.addAttribute("users", users);

        List<Roles> roles = ViewRolesService.viewRoles();
        model.addAttribute("roles", roles);

        return "ManageUsers";
    }
    
}
