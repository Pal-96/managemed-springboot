package com.managemed.managemedapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managemed.managemedapp.model.Product;
// import com.managemed.managemedapp.model.Product_refer;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // @Query("""
    //     select p.unitprice
    //     from Stock p
    //     where upper(p.product) = upper(:product)
    // """)
    // Integer findUnitPriceProduct(@Param("product") String product);
    Optional<Product> findByProduct(String product);
}