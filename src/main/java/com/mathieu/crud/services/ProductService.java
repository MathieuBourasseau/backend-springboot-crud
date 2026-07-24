package com.mathieu.crud.services;

import com.mathieu.crud.dto.ProductCreateDto;
import com.mathieu.crud.models.Product;
import com.mathieu.crud.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {

    // Declare the Repository needed to interact with DB
    private final ProductRepository productRepository;

    // Constructor of product service with ProductRepository methods
    // When the ProductService is built
    // ProductRepository searchs in bean with an object that contains all the
    // methods
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(ProductCreateDto dto) {

        // Get data from DTO
        // Then use it to create a new product
        Product newProduct = new Product(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice());

        // Save it in DB
        productRepository.save(newProduct);

        return newProduct;
    }

    public Product updateProduct(Long id, ProductCreateDto dto) {

        // Search in DB if the id of the product is found
        Product existingProduct = getProductById(id);

        // Then we change the former values by the news
        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());

        // Save the changes
        productRepository.save(existingProduct);

        return existingProduct;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
