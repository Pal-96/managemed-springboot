package com.managemed.managemedapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managemed.managemedapp.model.Cart;
import com.managemed.managemedapp.model.User;

@Repository
public interface AddCartProcedureRepository extends JpaRepository<Cart, String>  {

    @Procedure(procedureName = "add_cart")
    void addCart(
        @Param("in_product_name") String product,
        @Param("in_quantity") Integer quantity,
        @Param("in_tot_price") Integer price,
        @Param("in_username") String username,
        @Param("out_result") Integer result
    );
    
}
