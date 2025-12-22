// package com.managemed.managemedapp.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.managemed.managemedapp.model.Product_refer;
// import com.managemed.managemedapp.service.ProductService;

// @RestController
// //To allow Cross Origin requests from Tomcat server
// @CrossOrigin
// public class ProductController {

//     @Autowired
//     ProductService productService;

//     // @RequestMapping("/products")
//     @GetMapping("/products")
//     public ResponseEntity<List<Product_refer>> getProductInfo() {
//         return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
//     }

//     // @RequestMapping("/product/{id}")
//     @GetMapping("/product/{id}")
//     public ResponseEntity<Product_refer> getProductById(@PathVariable int id) {
//         Product_refer product = productService.getProductById(id);
//         if(product != null) {
//             return new ResponseEntity<>(product, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     // @RequestMapping("/addproduct")
//     @PostMapping("/product")
//     public ResponseEntity<Void> addProduct(@RequestBody Product_refer product) {
//         productService.addProduct(product);
//         return new ResponseEntity<>(HttpStatus.CREATED);
//     }

//     @PutMapping("/product/{id}")
//     public ResponseEntity<Void> updateProduct(@PathVariable int id, @RequestBody Product_refer updatedProduct) {
//         productService.updateProduct(id, updatedProduct);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

//     @DeleteMapping("/product/{id}")
//     public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
//         productService.deleteProduct(id);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
// }
