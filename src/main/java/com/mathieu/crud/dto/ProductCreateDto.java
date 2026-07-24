package com.mathieu.crud.dto;

import java.math.BigDecimal;

public class ProductCreateDto {

    // Only the fields the client is allowed to provide
    
    private String name;
    private String description;
    private BigDecimal price;

    // Empty constructor needed so Jackson can deserialize the JSON request body into this object.
    public ProductCreateDto() {}

    // Constructor used when building this DTO 
    public ProductCreateDto(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
