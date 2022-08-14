package com.example.ecommerce_website_project2.controller;

import com.example.ecommerce_website_project2.model.ApiResponse;
import com.example.ecommerce_website_project2.model.Product;
import com.example.ecommerce_website_project2.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity getProduct(){
        ArrayList<Product> products=productService.getProduct();
        return ResponseEntity.status(200).body(products);
    }
    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(productService.addProduct(product)) {
            return ResponseEntity.status(201).body(new ApiResponse("new product added", 201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong category id", 400));

    }
    @PutMapping("/{index}")
    public ResponseEntity updateProduct(@PathVariable int index,@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(productService.updateProduct(index,product)) {
            return ResponseEntity.status(201).body(new ApiResponse("Product updated", 201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index", 400));
    }
    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestParam String id){
        if(productService.deleteProduct(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id", 400));

    }
}