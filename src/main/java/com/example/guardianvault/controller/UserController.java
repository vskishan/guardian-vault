package com.example.guardianvault.controller;

import com.example.guardianvault.entity.User;
import com.example.guardianvault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/users/register")
  public String register(@RequestBody User user){
    userService.registerUser(user);
    return "User has been successfully registered!!";
  }

  @GetMapping("/users/{userName}")
  public User getUser(@PathVariable String userName){
    return userService.retrieveUserDetails(userName);
  }
}
