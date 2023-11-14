package com.example.guardianvault.service;

import com.example.guardianvault.entity.User;
import com.example.guardianvault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public void registerUser(User user){
    userRepository.save(user);
  }

  public User retrieveUserDetails(String userName){
    return userRepository.findByUsername(userName);
  }

}
