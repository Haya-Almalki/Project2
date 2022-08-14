package com.example.ecommerce_website_project2.controller;

import com.example.ecommerce_website_project2.model.ApiResponse;
import com.example.ecommerce_website_project2.model.User;
import com.example.ecommerce_website_project2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity getUsers(){
        ArrayList<User> users=userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("new user added",201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateUser(@PathVariable int index,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(userService.updateUser(index,user)){
            return ResponseEntity.status(201).body(new ApiResponse("User updated",201));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong index",400));

    }
    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam String id){
        if(userService.deleteUser(id)){
        return ResponseEntity.status(200).body(new ApiResponse("User deleted",200));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id",400));
    }
}