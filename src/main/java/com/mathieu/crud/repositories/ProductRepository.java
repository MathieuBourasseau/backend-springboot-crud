package com.mathieu.crud.repositories;

import com.mathieu.crud.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Precise to Spring Boot that this is a Repository
@Repository

// Methods set up by Product entity
public interface ProductRepository extends JpaRepository<Product, Long> { 

    // Create automatic methods from JpaRepository like :
        // save(product);
        // findAll():
        // deleteById(id)
    
} 
