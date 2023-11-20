package com.example.guardianvault.repository;

import com.example.guardianvault.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,String> {

  public Client findByClientId(String clientId);

}
