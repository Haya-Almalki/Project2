package com.example.ecommerce_website_project2.service;

import com.example.ecommerce_website_project2.model.Merchant;
import com.example.ecommerce_website_project2.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MerchantService {
    private ArrayList<Merchant> merchants=new ArrayList<>();
    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }
    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }
    public boolean updateMerchant(int index, Merchant merchant){
        if(index<0|| index>= merchants.size()){
            return false;
        }
        merchants.set(index,merchant);
        return true;
    }
    public boolean deleteMerchant(String id){
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId().equals(id)){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }
    public Merchant getMerchantId(String merchantId) {
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId().equals(merchantId)){
                return merchants.get(i);
            }
        }
        return null;
    }
}
