package com.example.guardianvault.service;

import com.example.guardianvault.config.JWTService;
import com.example.guardianvault.dto.AuthZCodeDTO;
import com.example.guardianvault.dto.TokenDTO;
import com.example.guardianvault.entity.AuthZCode;
import com.example.guardianvault.entity.Client;
import com.example.guardianvault.entity.User;
import com.example.guardianvault.repository.AuthZCodeRepository;
import com.example.guardianvault.repository.ClientRepository;
import com.example.guardianvault.repository.UserRepository;
import com.example.guardianvault.util.CommonUtils;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OAuth2Service {

  @Autowired
  UserRepository userRepository;

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  AuthZCodeRepository authZCodeRepository;

  @Autowired
  JWTService jwtService;

  @Transactional(readOnly = true)
  public boolean verifyUserDetails(AuthZCodeDTO authZCodeDTO){
    User user = userRepository.findByUsername(authZCodeDTO.getUsername());
    if(user == null){
      throw new BadCredentialsException("Username is incorrect or doesn't exist");
    }
    String encodedPwd = CommonUtils.passwordEncoder(authZCodeDTO.getPassword());
    return StringUtils.equals(user.getPassword(), encodedPwd);
  }

  @Transactional(readOnly = true)
  public boolean verifyClientDetails(String clientId){
    Client client = clientRepository.findByClientId(clientId);
    if(client == null){
      throw new EntityNotFoundException("Invalid client id");
    }
    return true;
  }

  @Transactional
  public String generateAuthZCode(AuthZCodeDTO authZCodeDTO){
    String code = RandomStringUtils.randomAlphanumeric(6);
    var encodedAuthZCode = CommonUtils.passwordEncoder(code);
    AuthZCode authZCode = new AuthZCode();
    authZCode.setClientId(authZCodeDTO.getClientId());
    authZCode.setRedirectURI(authZCodeDTO.getRedirectURI());
    authZCode.setState(authZCodeDTO.getState());
    authZCode.setAuthorizationCode(encodedAuthZCode);
    authZCodeRepository.save(authZCode);
    return code;
  }

  @Transactional(readOnly = true)
  public String generateTokenFromAuthZCode(TokenDTO tokenDTO){
    validateClientDetails(tokenDTO);
    validateAuthZCode(tokenDTO);
    return jwtService.generateToken(tokenDTO.getClientId());
  }

  private void validateClientDetails(TokenDTO tokenDTO){
    var client = clientRepository.findByClientId(tokenDTO.getClientId());
    if(client != null){
      String encodedPwd = CommonUtils.passwordEncoder(tokenDTO.getClientSecret());
      if(StringUtils.equalsIgnoreCase(client.getClientSecret(),encodedPwd)){
        return;
      }
    }
    throw new EntityNotFoundException("Invalid client details !!");
  }

  private void validateAuthZCode(TokenDTO tokenDTO){
    var authZCodeEntity = authZCodeRepository.findByClientId(tokenDTO.getClientId());
    if(authZCodeEntity != null){
      String encodedAuthZCode = CommonUtils.passwordEncoder(tokenDTO.getCode());
      if(StringUtils.equalsIgnoreCase(authZCodeEntity.getAuthorizationCode(),encodedAuthZCode)){
        return;
      }
    }
    throw new EntityNotFoundException("Authorization Code doesn't exist for client. Pls register and try again.");
  }
}
