package com.example.ecommerce_website_project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class BuyProduct {
    @NotEmpty(message = "user id must not be empty")
    @Size(min=3,message = "user id must be longer than 3 character")
    private String userId;
    @NotEmpty(message = "product id must not be empty")
    @Size(min=3, message = "product id must be longer than 3 character")
    private String productId;
    @NotEmpty(message = "merchant id must not be empty")
    @Size(min=3, message = "merchant id must be longer than 3 character")
    private String merchantId;
}
