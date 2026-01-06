package com.managemed.managemedapp.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.Payment;
import com.managemed.managemedapp.repository.OrderRepository;
import com.managemed.managemedapp.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Payment createOrUpdatePendingPayment(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new IllegalStateException("Order not found"));

        return paymentRepository
                .findByOrderIdAndPaymentStatus(orderId, "PENDING")
                .map(payment -> {
                    payment.setPaymentDate(LocalDate.now());
                    return payment;
                })
                .orElseGet(() -> {
                    Payment payment = new Payment();
                    payment.setOrder(order);
                    payment.setPaymentMode("CARD");
                    payment.setPaymentStatus("PENDING");
                    payment.setPaymentDate(LocalDate.now());
                    paymentRepository.save(payment);
                    return payment;
                });
    }
}
