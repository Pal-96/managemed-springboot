package com.managemed.managemedapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.managemed.managemedapp.model.Cart;
import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.CartRepository;
import com.managemed.managemedapp.repository.OrderRepository;
import com.managemed.managemedapp.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Order createPendingOrder(String username, int orderQty) {

        User user = userRepository.findById(username)
                .orElseThrow(() ->
                        new IllegalStateException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderQty(orderQty);
        order.setOrderStatus("PENDING");
        order.setOrderDate(LocalDate.now());

        Order savedOrder = orderRepository.save(order);

        List<Cart> reservedCartItems =
                cartRepository.findByUserUsernameAndCartStatusAndOrderIdIsNull(
                        username, "RESERVED");

        for (Cart cart : reservedCartItems) {
            cart.setOrderId(savedOrder.getId());
            cartRepository.save(cart);
        }

        return savedOrder;
    }
    
}
