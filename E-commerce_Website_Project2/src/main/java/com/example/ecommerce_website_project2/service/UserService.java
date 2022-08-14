package com.example.ecommerce_website_project2.service;
import com.example.ecommerce_website_project2.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserService {
    private ArrayList<User> users=new ArrayList<>();
    public ArrayList<User> getUsers() {
        return users;
    }
    public void addUser(User user){
        users.add(user);
    }
    public boolean updateUser(int index, User user){
        if(index<0|| index>= users.size()){
            return false;
        }
        users.set(index,user);
        return true;
    }
    public boolean deleteUser(String id){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
    public User getUserId(String userid) {
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId().equals(userid)){
                return users.get(i);
            }
        }
        return null;
    }


}
