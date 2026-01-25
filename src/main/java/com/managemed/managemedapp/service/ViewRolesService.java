package com.managemed.managemedapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.model.Roles;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.RoleRepository;

@Service
public class ViewRolesService {

    @Autowired
    RoleRepository roleRepository;
    public List<Roles> viewRoles() {
        return roleRepository.findByRoleNameNot("Customer");
    }
    
}
