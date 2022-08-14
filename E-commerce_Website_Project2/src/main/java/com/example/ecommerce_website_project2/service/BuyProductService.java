package com.example.ecommerce_website_project2.service;

import com.example.ecommerce_website_project2.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class BuyProductService {
    ArrayList<BuyProduct> buyProducts = new ArrayList<>();
    ProductService productService;
    UserService userService;
    MerchantService merchantService;
    MerchantStockService merchantStockService;

    public BuyProductService(ProductService productService, UserService userService,
                             MerchantService merchantService, MerchantStockService merchantStockService) {
        this.productService = productService;
        this.userService = userService;
        this.merchantService = merchantService;
        this.merchantStockService = merchantStockService;
    }

    public ArrayList<BuyProduct> getProduct() {
        return buyProducts;
    }

    public String BuyProduct(BuyProduct buyProduct) {
        User user=userService.getUserId(buyProduct.getUserId());
        Merchant merchant=merchantService.getMerchantId(buyProduct.getMerchantId());
        Product product=productService.getProductID(buyProduct.getProductId());
        if(user ==null){
            return "Invalid user id";
        }
        if(merchant ==null){
            return "Invalid merchant id";
        }
        if(product ==null) {
            return "Invalid product id";
        }
        int balance = user.getBalance();
        int price = product.getPrice();
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStocks();
        int invalidMerchantStock=-1;
        for (int i = 0; i < merchantService.getMerchants().size(); i++) {
            if (merchantStocks.get(i).getMerchantId().equals(buyProduct.getMerchantId())) {
                invalidMerchantStock = i;
                break;
            }
        }
        if(invalidMerchantStock==-1){
            return "The merchant don't have stock";
        }
        if (merchantStocks.get(invalidMerchantStock).getStock() == 0) {
            return "The merchant don't have the product in stock";
        }else if (balance < price) {
            return "Sorry your balance not allowed";
        } else {
            merchantStocks.get(invalidMerchantStock).setStock(merchantStocks.get(invalidMerchantStock).getStock() - 1);
            user.setBalance(balance - price);
            buyProducts.add(buyProduct);
            return "success";
        }
    }
}