package com.example.guardianvault.service;

import com.example.guardianvault.entity.VaultKeys;
import com.example.guardianvault.repository.VaultKeysRepository;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaultKeyService {

  @Autowired
  VaultKeysRepository vaultKeysRepository;

  public String getPublicKey(){
    VaultKeys latestKey = vaultKeysRepository.findTopByOrderByIdDesc();
    VaultKeys vaultKeys = vaultKeysRepository.findById(latestKey.getId()).orElse(null);
    String publicKey = Base64.getDecoder().decode(vaultKeys.getPublicKey()).toString();

    return publicKey;
  }

}
