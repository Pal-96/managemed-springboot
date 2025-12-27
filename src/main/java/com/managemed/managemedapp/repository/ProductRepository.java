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
    Optional<Product> findByProduct(String product);
}