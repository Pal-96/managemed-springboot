package com.managemed.managemedapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managemed.managemedapp.model.User;

@Repository
public interface AddUserProcedureRepository extends JpaRepository<User, String> {

    @Procedure(procedureName = "add_user")
    void addUser(
        @Param("in_username") String username,
        @Param("in_frstname") String firstname,
        @Param("in_lastname") String lastname,
        @Param("in_hashpwd") String password,
        @Param("in_role_id") Integer roleId
    );
}
