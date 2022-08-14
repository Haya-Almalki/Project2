package com.example.ecommerce_website_project2.controller;

import com.example.ecommerce_website_project2.model.ApiResponse;
import com.example.ecommerce_website_project2.model.MerchantStock;
import com.example.ecommerce_website_project2.service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantStock")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    public MerchantStockController(MerchantStockService merchantStockService) {
        this.merchantStockService = merchantStockService;
    }

    @GetMapping
    public ResponseEntity getMerchantStocks() {
        ArrayList<MerchantStock> users = merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/addMerchantStock")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(201).body(new ApiResponse("new merchant stock added", 201));
    }

    @PutMapping("/{index}")
    public ResponseEntity updateMerchantStock(@PathVariable int index, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message, 400));
        }
        if (merchantStockService.updateMerchantStock(index, merchantStock)) {
            return ResponseEntity.status(201).body(new ApiResponse("Merchant Stock updated", 201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index",400));
    }

    @DeleteMapping
    public ResponseEntity deleteMerchantStock(@RequestParam String id) {
        if (merchantStockService.deleteMerchantStock(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock deleted", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id", 400));
    }

    @PostMapping("/addProduct")
    public ResponseEntity addProductToStock(@RequestParam String productId,
                                            @RequestParam String merchantId,@RequestParam int stock){
        String msg=merchantStockService.addProductToStock(productId,merchantId,stock);
        if(msg.equals("success")){
            return ResponseEntity.status(200).body(new ApiResponse("Product added to stock", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse(msg, 400));
    }
}