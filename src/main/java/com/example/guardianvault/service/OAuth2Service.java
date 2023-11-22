package com.example.guardianvault.service;

import com.example.guardianvault.dto.AuthZCodeDTO;
import com.example.guardianvault.entity.User;
import com.example.guardianvault.repository.AuthZCodeRepository;
import com.example.guardianvault.repository.UserRepository;
import com.example.guardianvault.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class OAuth2Service {

  @Autowired
  UserRepository userRepository;

  public boolean verifyUserDetails(AuthZCodeDTO authZCodeDTO){
    User user = userRepository.findByUsername(authZCodeDTO.getUsername());
    if(user == null){
      throw new BadCredentialsException("Username is incorrect or doesn't exist");
    }
    String encodedPwd = CommonUtils.passwordEncoder(authZCodeDTO.getPassword());
    return StringUtils.equals(user.getPassword(), encodedPwd);
  }

}
