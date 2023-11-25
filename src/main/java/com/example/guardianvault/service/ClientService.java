package com.example.guardianvault.service;

import com.example.guardianvault.dto.ClientDTO;
import com.example.guardianvault.entity.Client;
import com.example.guardianvault.repository.ClientRepository;
import com.example.guardianvault.util.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  ObjectMapper objectMapper;

  @Transactional
  public ClientDTO registerClientDetails(ClientDTO clientDTO){
    Client client = setClientData(clientDTO);
    var clientSecret = passwordGenerator();
    client.setClientSecret(CommonUtils.passwordEncoder(clientSecret));
    clientRepository.save(client);
    clientDTO.setClientSecret(clientSecret);
    return clientDTO;
  }

  @Transactional(readOnly = true)
  public ClientDTO getClientDetails(String clientId){
    Client client = clientRepository.findByClientId(clientId);
    return objectMapper.convertValue(client, ClientDTO.class);
  }

  private String passwordGenerator(){
    return RandomStringUtils.randomAlphanumeric(10);
  }

  private Client setClientData(ClientDTO clientDTO){
    var client = new Client();
    client.setClientId(clientDTO.getClientId());
    client.setScopes(clientDTO.getScopes());
    return client;
  }

}
