package com.example.ecommerce_website_project2.controller;

import com.example.ecommerce_website_project2.model.ApiResponse;
import com.example.ecommerce_website_project2.model.BuyProduct;
import com.example.ecommerce_website_project2.service.BuyProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user/buy")
public class BuyProductController {
    private final BuyProductService buyProductService;

    public BuyProductController(BuyProductService buyProductService) {
        this.buyProductService = buyProductService;
    }
    @GetMapping
    public ResponseEntity getSellingProduct() {
        ArrayList<BuyProduct> buyProducts = buyProductService.getProduct();
        return ResponseEntity.status(200).body(buyProducts);
    }

    @PostMapping
    public ResponseEntity buyProduct(@RequestBody @Valid BuyProduct buyProduct, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        String msg = buyProductService.BuyProduct(buyProduct);
        if (msg.equals("success")) {
            return ResponseEntity.status(200).body(new ApiResponse("Purchase Successful", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse(msg, 400));
    }
}


