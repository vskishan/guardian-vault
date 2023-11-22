package com.example.guardianvault.service;

import com.example.guardianvault.entity.User;
import com.example.guardianvault.repository.UserRepository;
import com.example.guardianvault.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public void registerUser(User user){
    user.setPassword(CommonUtils.passwordEncoder(user.getPassword()));
    userRepository.save(user);
  }

  public User retrieveUserDetails(String userName){
    return userRepository.findByUsername(userName);
  }

}
