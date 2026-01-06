package com.managemed.managemedapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findFirstByUserAndOrderStatus(User user, String orderStatus);
    
}
