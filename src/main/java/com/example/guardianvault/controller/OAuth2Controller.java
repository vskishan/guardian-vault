package com.example.guardianvault.controller;

import com.example.guardianvault.dto.AuthZCodeDTO;
import com.example.guardianvault.service.OAuth2Service;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

  @Autowired
  OAuth2Service oAuth2Service;

  @PostMapping("/verify-details")
  public String verifyUser(@RequestBody AuthZCodeDTO authZCodeDTO){
    if(StringUtils.equalsIgnoreCase(authZCodeDTO.getResponseType(), "code")){
        if(oAuth2Service.verifyUserDetails(authZCodeDTO)
            || oAuth2Service.verifyClientDetails(authZCodeDTO.getClientId())) {
          return "You are verified !";
        }
        else{
          throw new EntityNotFoundException("Incorrect details");
        }
    }
    return "Not supported";
  }
}
