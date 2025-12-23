package com.managemed.managemedapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managemed.managemedapp.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByRoleName(String roleName);
}
