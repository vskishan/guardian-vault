package com.example.guardianvault.repository;

import com.example.guardianvault.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> {

  Client findByClientId(String clientId);

}
