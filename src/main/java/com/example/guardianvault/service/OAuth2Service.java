package com.example.guardianvault.service;

import com.example.guardianvault.dto.AuthZCodeDTO;
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
    AuthZCode authZCode = new AuthZCode();
    authZCode.setClientId(authZCodeDTO.getClientId());
    authZCode.setRedirectURI(authZCodeDTO.getRedirectURI());
    authZCode.setState(authZCodeDTO.getState());
    authZCode.setAuthorizationCode(code);
    authZCodeRepository.save(authZCode);
    return code;
  }

}
