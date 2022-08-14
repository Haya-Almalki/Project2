package com.example.ecommerce_website_project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Product {
    @NotEmpty(message = "id must not be empty")
    @Size(min=3,message = "id must be longer than 3 character")
    private String id;
    @NotEmpty(message = "name must not be empty")
    @Size(min=3,message = "name must be longer than 3 character")
    private String name;
    @NotNull(message = "price must be not empty")
    @Positive(message = "price must be positive number")
    private int price;
    @NotEmpty(message = "category id must not be empty")
    @Size(min=3,message = "category id must be longer than 3 character")
    private String categoryId;
}
