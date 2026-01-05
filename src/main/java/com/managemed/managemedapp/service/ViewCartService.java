package com.managemed.managemedapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.model.Cart;
import com.managemed.managemedapp.repository.CartRepository;

@Service
public class ViewCartService {

    @Autowired
    private CartRepository cartRepository;

    public Long getCartCount(String username) {
        return cartRepository.countByUserUsernameAndOrderIdIsNull(username);
        
    }

    public List<Cart> getAllCartItems(String username) {
        return cartRepository.findByUserUsernameAndOrderIdIsNull(username);
    }

    public double getTotalAmount(String username) {
        List<Cart> cartItems = getAllCartItems(username);
        double total = 0.0;
        for (Cart item : cartItems) {
            if (item.getCartStatus() == null) {
                total += item.getPrice();
            }
        }
        System.out.println("cart total:" + total);
        return total;
    }
    
}
