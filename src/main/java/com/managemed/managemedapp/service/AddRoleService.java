package com.managemed.managemedapp.service;

import java.util.Optional;
import com.managemed.managemedapp.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.repository.RoleRepository;

@Service
public class AddRoleService {
    @Autowired
    RoleRepository roleRepository;
    
    public void addEditRole(String roleName, String roleId, String action) {
		if (action.equals("add")) {
            Optional<Roles> role= roleRepository.findByRoleName(roleName);
            if(role.isPresent()) {
                // Role already exists, handle accordingly
                return;
            }

            Roles newRole = new Roles();
            newRole.setRole(roleName);
			roleRepository.save(newRole);
		}

        if (action.equals("edit")) {
            int Id = Integer.parseInt(roleId);
            Optional<Roles> role= roleRepository.findById(Id);
            if(role.isPresent()) {
                Roles existingRole = role.get();
                // Update the role as needed
                existingRole.setRole(roleName);
                roleRepository.save(existingRole);
            }
        }

        if (action.equals("delete")) {
            int Id = Integer.parseInt(roleId);
            Optional<Roles> role= roleRepository.findById(Id);
			if(role.isPresent()) {
                roleRepository.deleteById(Id);
            }
            
            return;
		}
    }
    }

