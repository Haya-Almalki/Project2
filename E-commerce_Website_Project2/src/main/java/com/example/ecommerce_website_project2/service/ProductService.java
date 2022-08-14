package com.example.ecommerce_website_project2.service;
import com.example.ecommerce_website_project2.model.Category;
import com.example.ecommerce_website_project2.model.Product;
import com.example.ecommerce_website_project2.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProductService {
    private ArrayList<Product> products=new ArrayList<>();
    CategoryService categoryService;
    public ProductService(@Lazy CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public ArrayList<Product> getProduct() {
        return products;
    }
    public boolean addProduct(Product product){
        ArrayList<Category> categories=categoryService.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            if(product.getCategoryId().equals(categories.get(i).getId())){
                products.add(product);
                return true;
            }
        }
        return false;
    }
    public boolean updateProduct(int index, Product product){
        if(index<0|| index>= products.size()){
            return false;
        }
        ArrayList<Category> categories=categoryService.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            if(product.getCategoryId().equals(categories.get(i).getId())){
                products.set(index,product);
                return true;
            }
        }
        return false;
    }
    public boolean deleteProduct(String id){
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }
    public Product getProductID(String productId) {
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId().equals(productId)){
                return products.get(i);
            }
        }
        return null;
    }
}