package com.managemed.managemedapp.repository;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findFirstByUserAndOrderStatus(User user, String orderStatus);
    
}
