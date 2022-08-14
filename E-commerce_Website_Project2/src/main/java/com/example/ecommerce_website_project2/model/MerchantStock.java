package com.example.ecommerce_website_project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
@AllArgsConstructor @Data
public class MerchantStock {
    @NotEmpty(message = "id must be not empty")
    @Size(min=3,message = "id must be longer than 3 character")
    private String id;
    @NotEmpty(message = "product id must be not empty")
    @Size(min=3,message = "product id must be longer than 3 character")
    private String productId;
    @NotEmpty(message = "merchant id must be not empty")
    @Size(min=3,message = "merchant id must be longer than 3 character")
    private String merchantId;
    @NotNull(message = "stock must be not empty")
    @Range(min=10,message = "stock must be more than 10")
    private int stock;
}