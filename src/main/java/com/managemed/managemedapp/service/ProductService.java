// package com.managemed.managemedapp.service;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.managemed.managemedapp.model.Product_refer;
// import com.managemed.managemedapp.repository.ProductRepository;

// @Service
// public class ProductService {

//     @Autowired
//     ProductRepository productRepository;
//     // ArrayList<Product> products = new ArrayList<>(Arrays.asList(new Product(1, "Laptop", 400.0), new Product(2, "Desktop", 1000.0), new Product(3, "Mouse", 30.0), new Product(4, "Headset", 70.0), new Product(5, "IPhone", 999.0)));

//     public List<Product_refer> getAllProducts() {
//         return productRepository.findAll();
//     }

//     public Product_refer getProductById(int id) {
//         return productRepository.findById(id).orElse(null);
//         // for (Product product : products) {
//         //     if (product.getId() == id) {
//         //         return product;
//         //     }
//         // }
//         // return null;
//     }

//     public void addProduct(Product_refer product) {
//         productRepository.save(product);
//         // products.add(product);
//     }

//     public void updateProduct(int id, Product_refer updatedProduct) {
//         productRepository.save(updatedProduct);
//         // for (int i = 0; i < products.size(); i++) {
//         //     Product product = products.get(i);
//         //     if (product.getId() == id) {
//         //         products.set(i, updatedProduct);
//         //         return;
//         //     }
//         // }
//     }

//     public void deleteProduct(int id) {
//         productRepository.deleteById(id);
//         // products.removeIf(product -> product.getId() == id);
//     }
// }
