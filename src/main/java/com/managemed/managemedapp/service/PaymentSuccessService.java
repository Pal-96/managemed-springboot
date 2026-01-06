package com.managemed.managemedapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managemed.managemedapp.model.Cart;
import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.Payment;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.CartRepository;
import com.managemed.managemedapp.repository.OrderRepository;
import com.managemed.managemedapp.repository.PaymentRepository;
import com.managemed.managemedapp.repository.UserRepository;

@Service
public class PaymentSuccessService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public long proceedSale(String username) {

        // 1. Load user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        // 2. Lock and fetch pending order
        Order order = orderRepository
                .findFirstByUserAndOrderStatus(user, "PENDING")
                .orElseThrow(() -> new IllegalStateException("No pending order found"));

        // 3. Complete payment
        Payment payment = paymentRepository.findByOrderIdAndPaymentStatus(order.getId(), "PENDING")
                .orElseThrow(() -> new IllegalStateException("Payment not found"));

        payment.setPaymentStatus("COMPLETED");
        payment.setPaymentDate(LocalDate.now());
        paymentRepository.save(payment);

        // 4. Complete order
        order.setOrderStatus("COMPLETED");
        order.setOrderDate(LocalDate.now());
        orderRepository.save(order);

        // 5. Mark cart items as purchased
        List<Cart> reservedItems =
                cartRepository.findByUserAndCartStatus(user, "RESERVED");

        for (Cart cart : reservedItems) {
            cart.setOrderId(order.getId());
            cart.setCartStatus("PURCHASED");
        }

        cartRepository.saveAll(reservedItems);

        // 6. Remaining cart count
        return cartRepository.countByUserAndOrderIdIsNull(user);
    }
}

