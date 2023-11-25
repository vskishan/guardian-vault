package com.example.guardianvault.controller;

import com.example.guardianvault.dto.AuthZCodeDTO;
import com.example.guardianvault.dto.TokenDTO;
import com.example.guardianvault.dto.TokenResponseDTO;
import com.example.guardianvault.service.OAuth2Service;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(oAuth2Service.verifyUserDetails(authZCodeDTO) || oAuth2Service.verifyClientDetails(authZCodeDTO.getClientId())) {
          return oAuth2Service.generateAuthZCode(authZCodeDTO);
        }
        else{
          throw new EntityNotFoundException("Incorrect details");
        }
    }
    return "Not supported";
  }

  @PostMapping("/token")
  public TokenResponseDTO getToken(@RequestBody TokenDTO tokenDTO){
    if(StringUtils.equalsIgnoreCase(tokenDTO.getGrantType(), "code")){
      TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
      tokenResponseDTO.setToken(oAuth2Service.generateTokenFromAuthZCode(tokenDTO));
      return tokenResponseDTO;
    }
    return null;
  }
}
