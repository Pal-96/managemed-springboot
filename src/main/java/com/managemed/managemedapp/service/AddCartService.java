package com.managemed.managemedapp.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.managemed.managemedapp.dao.DAOImpl;
import com.managemed.managemedapp.model.Cart;
import com.managemed.managemedapp.model.Product;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.CartRepository;
import com.managemed.managemedapp.repository.ProductRepository;
import com.managemed.managemedapp.repository.UserRepository;
import com.managemed.managemedapp.security.JWTUtil;

/**
 * Servlet implementation class MedCart
 */
@Service
public class AddCartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;
	private int quantity;
	HttpSession session;

	public boolean handleAddToCart(String productName, String cartquan, String addtocart, String username,
			HttpSession session) {
		System.out.println("Inside MedCart");
		System.out.println("Inside Med Cart Product:" + productName);
		System.out.println("Added?:" + addtocart);
		System.out.println("Med added:" + productName);
		this.quantity = Integer.parseInt(cartquan);
		if (quantity<=0) {
			session.setAttribute("med", "not added");
			return false;
		}

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));

		Product product = productRepository.findByProduct(productName)
                .orElseThrow(() -> new IllegalStateException("Product not found"));

		int unitPrice = product.getUnitprice();

		Cart cart = cartRepository
                .findByUserUsernameAndProductProductAndOrderIdIsNull(username, productName)
                .orElse(new Cart());

        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setPrice(quantity * unitPrice);
        cart.setCartStatus(null);
        cart.setOrderId(null);

        cartRepository.save(cart);
		long cartCount = cartRepository.countByUserUsernameAndOrderIdIsNull(username);

        session.setAttribute("med", "added");
        session.setAttribute("quan", quantity);
        session.setAttribute("cartcount", cartCount);

        return true;
	}

	public void handleRemoveFromCart(String productName, String username, HttpSession session) {
		System.out.println("Removed Med added:" + productName);
		Cart cart = cartRepository
                .findByUserUsernameAndProductProductAndOrderIdIsNull(username, productName)
				.orElseThrow(() -> new IllegalStateException("Product not found in cart"));
		// cartRepository.deleteByUserUsernameAndProductProduct(username, productName);
		cartRepository.delete(cart);
		long cartCount = cartRepository.countByUserUsernameAndOrderIdIsNull(username);
        session.setAttribute("med", "removed");
        session.setAttribute("cartcount", cartCount);
	}
}
