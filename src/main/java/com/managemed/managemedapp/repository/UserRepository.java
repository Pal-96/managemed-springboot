package com.managemed.managemedapp.repository;
import com.managemed.managemedapp.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    @Query("""
        select u from User u
        join u.role r
        where r.roleName <> :excludedRole
    """)
    List<User> findAllExceptRole(@Param("excludedRole") String excludedRole);

}

