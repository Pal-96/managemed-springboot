package com.managemed.managemedapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managemed.managemedapp.model.Product;
import com.managemed.managemedapp.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(String product) {
        if (product == null || product.isBlank()) {
            return productRepository.findAll();
        }
        return productRepository.findByProduct(product);
    }
}

