package com.mathieu.crud.models;

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

}
