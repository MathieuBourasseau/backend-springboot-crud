package com.mathieu.crud.controllers;

import com.mathieu.crud.dto.ProductCreateDto;
import com.mathieu.crud.models.Product;
import com.mathieu.crud.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Route that consumes the method getAllProducts of productService
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Route that consumes the method getProductById of productService
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Route in POST to add a new product
   @PostMapping
   public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDto dto){
    Product createdProduct = productService.createProduct(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
   }


}
