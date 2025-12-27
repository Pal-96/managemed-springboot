package com.managemed.managemedapp.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.dao.DAOImpl;
import com.managemed.managemedapp.model.Product;
import com.managemed.managemedapp.repository.ProductRepository;

@Service
public class AddRemoveService {

    private DAOImpl dao;
    private Product product;
    @Autowired
    ProductRepository productRepository;

    public void handleAddRemove(String productTitle, 
        String action, 
        Integer quantity, 
        Integer unitprice,
        String description, 
        HttpSession session) {
        // dao = DAOImpl.getInstance();
			session.setAttribute("action", action);
			if (action.equals("add") && quantity > 0) {
                addProduct(productTitle, quantity, unitprice, description, session);	
				// response.setContentType("text/html");
                // response.getWriter().write("<script>window.parent.loadContent('DisplayAll.jsp');</script>");
			}
			else if (action.equals("edit") && quantity > 0) {
				System.out.println("Inside updated RemoveImpl");
				System.out.println("Product being updated:"+ productTitle);
                editProduct(productTitle, quantity, unitprice, description, session);
			}
			else if (action.equals("deletestock")) {
				System.out.println("Inside delete stock");
                deleteProduct(productTitle, session);
			}
			else if (quantity <= 0) {
				System.out.println(quantity);
				session.setAttribute("quantity", quantity);
			}
    }

    private void deleteProduct(String productTitle, HttpSession session) {
        Optional<Product> product = productRepository.findByProduct(productTitle);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            session.setAttribute("productdeleted", true);
        } else {
            session.setAttribute("productnotfound", true);
        }
    }

    private void editProduct(String productTitle, Integer quantity, Integer unitprice, String description,
            HttpSession session) {
        Optional<Product> product = productRepository.findByProduct(productTitle);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setQuantity(quantity);
            existingProduct.setUnitprice(unitprice);
            existingProduct.setDescription(description);
            productRepository.save(existingProduct);
            session.setAttribute("productupdated", true);

        } else {
            session.setAttribute("productnotfound", true);
        }
    }

    private void addProduct(String productTitle, Integer quantity, Integer unitprice, String description, HttpSession session) {
        if (productRepository.findByProduct(productTitle).isPresent()) {
            session.setAttribute("productexists", true);
            return;
        }
        product = new Product();
        product.setProduct(productTitle);
        product.setQuantity(quantity);
        product.setUnitprice(unitprice);
        product.setDescription(description);
        productRepository.save(product);
        session.setAttribute("quantity", quantity);
    }
    
}
