package com.example.ecommerce_website_project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class User {
    @NotEmpty(message = "id must not be empty")
    @Size(min=3,message = "user id must be longer than 3 character")
    private String id;
    @NotEmpty(message = "username must not be empty")
    @Size(min=5,message = "username must be longer than 5 character")
    private String username;
    @NotEmpty(message = "password must not be empty")
    @Pattern(regexp="^[a-zA-Z0-9]{6}",message="Password length must be 6 and have characters and digits")
    private String password;
    @NotEmpty(message = "Email must be not empty")
    @Email(message = "Invalid email")
    private String email;
    @NotEmpty(message = "role must be not empty")
    @Pattern(regexp = "(?i)^(Admin|Customer)$",message = "role must be Admin or Customer")
    private String role;
    @NotNull(message = "balance must be not empty")
    @Positive(message = "balance must be positive number")
    private int balance;
}
