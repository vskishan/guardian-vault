package com.example.guardianvault.controller;

import com.example.guardianvault.entity.VaultKeys;
import com.example.guardianvault.service.VaultKeyService;
import java.security.Key;
import java.security.PublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTPublicKey {

  @Autowired
  VaultKeyService vaultKeyService;


  @GetMapping("/oauth2/jwks")
  public PublicKey getJWTPubKey(){
    return vaultKeyService.getPublicKey();
  }

}
