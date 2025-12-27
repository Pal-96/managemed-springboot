package com.managemed.managemedapp.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.managemed.managemedapp.dao.DAOImpl;
import com.managemed.managemedapp.model.Product;
import com.managemed.managemedapp.service.AddRemoveService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class AddRemoveController {

	@Autowired
	AddRemoveService addRemoveService;

	@PostMapping("/addrem")
	public String handleAddRemove(@RequestParam String product,
		@RequestParam String action,
		@RequestParam(required = false) Integer quantity,
		@RequestParam(required = false) Integer unitprice,
		@RequestParam(required = false) String description,
		HttpSession session) throws ServletException, IOException {
			addRemoveService.handleAddRemove(product, action, quantity, unitprice, description, session);
			return "redirect:/displayall";
	}

}
