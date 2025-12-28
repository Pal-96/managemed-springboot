package com.managemed.managemedapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.managemed.managemedapp.service.AddRoleService;

@Controller
public class AddRoleController {

    @Autowired
    AddRoleService addRoleService;

    @PostMapping("/addrole")
    public String handleAddEditRole(@RequestParam(required = false) String role,
        @RequestParam(required = false) String roleId,
        @RequestParam String action
    ) {
        addRoleService.addEditRole(role, roleId, action);
        return "redirect:/manageroles";
    }
}
