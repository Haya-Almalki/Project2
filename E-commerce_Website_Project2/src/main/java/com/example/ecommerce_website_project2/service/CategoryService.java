package com.example.ecommerce_website_project2.service;

import com.example.ecommerce_website_project2.model.Category;
import com.example.ecommerce_website_project2.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CategoryService {
    private ArrayList<Category> categories=new ArrayList<>();
    private ProductService productService;
    public CategoryService(ProductService productService) {
        this.productService = productService;
    }
    public ArrayList<Category> getCategories() {
        return categories;
    }
    public void addCategory(Category category){
        categories.add(category);
    }
    public boolean updateCategory(int index, Category category){
        if(index<0|| index>= categories.size()){
            return false;
        }
        ArrayList<Product> products=productService.getProduct();
        for (int i = 0; i < products.size(); i++) {
            if(categories.get(index).getId().equals(products.get(i).getCategoryId())){
                products.get(i).setCategoryId(category.getId());
            }
        }
        categories.set(index,category);
        return true;
    }
    public boolean deleteCategory(String id){
        ArrayList<Product> products=productService.getProduct();
        for (int i = 0; i <categories.size() ; i++) {
            if(categories.get(i).getId().equals(id)){
                for (int j = 0; j < products.size(); j++) {
                    if(id.equals(products.get(j).getCategoryId())){
                        products.remove(j);
                    }
                    }
                categories.remove(i);
                return true;
            }
        }
        return false;
    }
}