package com.managemed.managemedapp.service;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.managemed.managemedapp.dao.DAOImpl;
import com.managemed.managemedapp.security.JWTUtil;

/**
 * Servlet implementation class MedCart
 */
@Service
public class AddCartService {
	private int quantity;
	private String product;
	private int result;
	HttpSession session;
	private String token;
	private String username;

	public boolean handleAddToCart(String product, String cartquan, String addtocart, String username,
			HttpSession session) {
		System.out.println("Inside MedCart");
		System.out.println("Inside Med Cart Product:" + product);
		System.out.println("Added?:" + addtocart);
		System.out.println("Med added:" + product);
		this.product = product;
		this.quantity = Integer.parseInt(cartquan);
		this.session = session;
		System.out.println("Inside Med Cart logged in by:" + username);
		DAOImpl dao = DAOImpl.getInstance();
		try {
			result = dao.addcart(product, quantity, username);
			System.out.println("no stock:" + result);
			if (result > 0) {
				int cartcount = dao.getCartCount(username);
				session.setAttribute("med", "added");
				session.setAttribute("quan", quantity);
				session.setAttribute("cartcount", cartcount);
				return true;
			}

			else if (result == 0 || quantity < 0) {
				session.setAttribute("med", "not added");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (cartquan == null || cartquan.isEmpty()) {
			session.setAttribute("med", "not added");
			return false;
		}

		return false;
	}

	public void handleRemoveFromCart(String product, String username, HttpSession session) {
		System.out.println("Removed Med added:" + product);
		this.product = product;
		DAOImpl dao = DAOImpl.getInstance();
		try {
			result = dao.removecart(product);
			System.out.println("no stock:" + result);
			if (result > 0) {
				int cartcount = dao.getCartCount(username);
				session.setAttribute("med", "removed");
				session.setAttribute("cartcount", cartcount);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
