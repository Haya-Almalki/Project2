package com.example.ecommerce_website_project2.controller;

import com.example.ecommerce_website_project2.model.ApiResponse;
import com.example.ecommerce_website_project2.model.Category;
import com.example.ecommerce_website_project2.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity getCategories(){
        ArrayList<Category> categories=categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }
    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse("new category added",201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateCategory(@PathVariable int index,@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(categoryService.updateCategory(index, category)) {
            return ResponseEntity.status(201).body(new ApiResponse("Category updated", 201));
            }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index", 400));
    }
    @DeleteMapping
    public ResponseEntity deleteCategory(@RequestParam String id){
        if(categoryService.deleteCategory(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Category deleted", 200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id", 400));

    }
}
