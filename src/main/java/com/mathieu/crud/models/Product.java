package com.mathieu.crud.models;

import java.math.BigDecimal;

// JPA annotations for entity mapping (implemented by Hibernate)
import jakarta.persistence.*;

// Entity specifies that the class is for a table in a db
@Entity

// Force the name of table in the db
@Table(name="products")
public class Product {

    // This attribute is defined as a PK and auto incremented
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Prevent to get an empty product name 
    @Column(nullable = false)
    private String name;

    // Having a description is an obligation for a product
    @Column(nullable = false)
    private String description;

    // Having a price is an obligation for a product
    @Column(nullable = false)
    private BigDecimal price;

}
