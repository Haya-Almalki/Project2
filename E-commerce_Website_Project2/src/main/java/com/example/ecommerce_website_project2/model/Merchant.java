package com.example.ecommerce_website_project2.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Merchant {
    @NotEmpty(message = "id must not be empty")
    @Size(min=3,message = "id must be longer than 3 character")
    private String id;
    @NotEmpty(message = "name must not be empty")
    @Size(min=3,message = "name must be longer than 3 character")
    private String name;
}
