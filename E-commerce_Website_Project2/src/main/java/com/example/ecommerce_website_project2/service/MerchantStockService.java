package com.example.ecommerce_website_project2.service;

import com.example.ecommerce_website_project2.model.Merchant;
import com.example.ecommerce_website_project2.model.MerchantStock;
import com.example.ecommerce_website_project2.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class MerchantStockService {
    private ArrayList<MerchantStock> merchantStocks=new ArrayList<>();
    MerchantService merchantService;
    ProductService productService;
    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public MerchantStockService(MerchantService merchantService, ProductService productService) {
        this.merchantService = merchantService;
        this.productService = productService;
    }

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }
    public boolean updateMerchantStock(int index, MerchantStock merchantStock){
        if(index<0|| index>= merchantStocks.size()){
            return false;
        }
        merchantStocks.set(index,merchantStock);
        return true;
    }
    public boolean deleteMerchantStock(String id){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }
    public String addProductToStock(String productId,String merchantId , int stock){
        ArrayList<Product> products=productService.getProduct();
        ArrayList<Merchant>merchants=merchantService.getMerchants();
        int invalidProduct=-1;
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId().equals(productId)){
                invalidProduct=i;
                break;
            }
        }
        if(invalidProduct==-1){
            return "wrong product id";
        }
        int invalidMerchant=-1;
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId().equals(merchantId)){
                invalidMerchant=i;
                break;
            }
        }
        if(invalidMerchant==-1){
            return "wrong merchant id";
        }
        int id = 100 + (int)(Math.random() * ((99999 - 100) + 1));
        MerchantStock merchantStock=new MerchantStock(id+"",productId,merchantId,stock);
        merchantStocks.add(merchantStock);
        return "success";
    }
}