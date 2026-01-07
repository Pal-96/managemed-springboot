package com.managemed.managemedapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.managemed.managemedapp.model.Product;
// import com.managemed.managemedapp.model.Product_refer;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProduct(String product);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Product s
        SET s.quantity = s.quantity + :qty
        WHERE s.product = :product
    """)
    void restoreQuantity(
            @Param("qty") int qty,
            @Param("product") String product
    );
}