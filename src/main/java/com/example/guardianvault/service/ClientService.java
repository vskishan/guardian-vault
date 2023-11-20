package com.example.guardianvault.service;

import com.example.guardianvault.dto.ClientDTO;
import com.example.guardianvault.entity.Client;
import com.example.guardianvault.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  ObjectMapper objectMapper;

  public Client registerClientDetails(ClientDTO clientDTO){
    Client client = objectMapper.convertValue(clientDTO, Client.class);
    client.setClientSecret(passwordGenerator());
    return clientRepository.save(client);
  }

  public ClientDTO getClientDetails(String clientId){
    Client client = clientRepository.findByClientId(clientId);
    return objectMapper.convertValue(client, ClientDTO.class);
  }

  private String passwordGenerator(){
    return RandomStringUtils.randomAlphanumeric(10);
  }

}
