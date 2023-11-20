package com.example.guardianvault.controller;

import com.example.guardianvault.dto.ClientDTO;
import com.example.guardianvault.entity.Client;
import com.example.guardianvault.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @Autowired
  ClientService clientService;

  @PostMapping("/clients/register")
  public Client registerClient(@RequestBody ClientDTO clientDTO){
    return clientService.registerClientDetails(clientDTO);
  }

  @GetMapping("/clients/{clientId}/details")
  public ClientDTO getClient(@PathVariable String clientId){
    return clientService.getClientDetails(clientId);
  }

}
