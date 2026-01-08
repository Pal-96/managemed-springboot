package com.managemed.managemedapp.repository;
import com.managemed.managemedapp.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    // List<User> findByRoleIdNot(int roleId);

}

