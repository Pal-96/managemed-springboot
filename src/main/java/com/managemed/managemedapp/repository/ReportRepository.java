package com.managemed.managemedapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managemed.managemedapp.model.Product;

@Repository
public interface ReportRepository extends JpaRepository<Product, String> {

    @Query("""
        select count(u.username)
        from User u
        where u.role.roleName = :role
    """)
    int countUsersByRole(@Param("role") String role);

    @Query("""
        select count(o.id)
        from Order o
        where o.orderStatus = :status
    """)
    int countOrdersByStatus(@Param("status") String status);

    @Query("""
        select count(p)
        from Product p
        where p.quantity > 0
    """)
    int countAvailableProducts();
}

