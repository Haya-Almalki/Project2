package com.example.ecommerce_website_project2.controller;

import com.example.ecommerce_website_project2.model.ApiResponse;
import com.example.ecommerce_website_project2.model.Merchant;
import com.example.ecommerce_website_project2.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;
    public MerchantController(MerchantService merchantService) {
        this.merchantService=merchantService;
    }
    @GetMapping
    public ResponseEntity getMerchants(){
        ArrayList<Merchant> merchants=merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchants);
    }
    @PostMapping("/addMerchant")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(201).body(new ApiResponse("new merchant added",201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateMerchant(@PathVariable int index,@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(merchantService.updateMerchant(index, merchant)) {
            return ResponseEntity.status(201).body(new ApiResponse("Merchant updated", 201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index",400));
    }
    @DeleteMapping
    public ResponseEntity deleteMerchant(@RequestParam String id){
        if(merchantService.deleteMerchant(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id",400));

    }
}