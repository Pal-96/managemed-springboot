package com.managemed.managemedapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.managemed.managemedapp.model.Cart;
import com.managemed.managemedapp.model.Order;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.CartRepository;
import com.managemed.managemedapp.repository.OrderRepository;
import com.managemed.managemedapp.repository.PaymentRepository;
import com.managemed.managemedapp.repository.ProductRepository;
import com.managemed.managemedapp.repository.UserRepository;

@Service
public class RestoreStockService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void restoreStock(String username) {

        List<Cart> reservedItems =
                cartRepository.findByUserUsernameAndCartStatus(
                        username, "RESERVED"
                );

        for (Cart cart : reservedItems) {
            int cartQuantity = cart.getQuantity();
            String product = cart.getProduct().getProduct();

            // restore stock quantity
            productRepository.restoreQuantity(cartQuantity, product);

            // clear cart status
            cartRepository.clearCartStatus(
                    username,
                    product,
                    null,
                    null,
                    List.of("RESERVED", "UNAVAILABLE")
            );
        }
    }

    public void deletePendingPaymentEntry(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Order> pendingOrder = orderRepository.findFirstByUserAndOrderStatus(user.get(), "PENDING");
        paymentRepository.deleteByOrderId(pendingOrder.get().getId());        
    }

    public void deletePendingOrderEntry(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Order> pendingOrder = orderRepository.findFirstByUserAndOrderStatus(user.get(), "PENDING");
        orderRepository.delete(pendingOrder.get());
    }

    @Transactional
    public void cancelPayment(String username) {
        deletePendingPaymentEntry(username);
        restoreStock(username);
        deletePendingOrderEntry(username);
    }
    
}
