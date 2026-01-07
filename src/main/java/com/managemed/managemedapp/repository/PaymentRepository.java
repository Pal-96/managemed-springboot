package com.managemed.managemedapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.Payment;
import com.managemed.managemedapp.model.User;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderIdAndPaymentStatus(
            Long orderId, String paymentStatus);

    Optional<Payment> findByOrder(Order order);

    void deleteByOrderId(Long orderId);
}
