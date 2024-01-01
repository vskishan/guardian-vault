package com.example.guardianvault.config;


import com.example.guardianvault.entity.Client;
import com.example.guardianvault.entity.VaultKeys;
import com.example.guardianvault.repository.ClientRepository;
import com.example.guardianvault.repository.VaultKeysRepository;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTService {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  VaultKeysRepository vaultKeysRepository;

  public String generateToken(String clientId) {
    Map<String, Object> claims = new HashMap<>();
    var client = clientRepository.findByClientId(clientId);
    setClaims(client, claims);
    return createToken(claims);
  }

  private String createToken(Map<String, Object> claims) {
    KeyPair keyPair = generateRSAKeys();
    Key privateKey = keyPair.getPrivate();
    Key publicKey = keyPair.getPublic();
    persistKeys(privateKey, publicKey);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuer("guardian-vault")
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(privateKey, SignatureAlgorithm.RS256).compact();
  }

  private Map<String, Object> setClaims(Client client, Map<String, Object> claims){
    claims.put("scp", new ArrayList<>(client.getScopes()));
    claims.put("cid", client.getClientId());
    return claims;
  }

  private KeyPair generateRSAKeys() {
    KeyPairGenerator keyPairGenerator = null;
    try{
      keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    }
    catch (NoSuchAlgorithmException exception){
      System.out.println("Unable to generate keys!");
    }
    keyPairGenerator.initialize(2048);
    return keyPairGenerator.generateKeyPair();
  }

  private void persistKeys(Key privateKey, Key publicKey){
    VaultKeys vaultKeys = new VaultKeys();
    vaultKeys.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    vaultKeys.setPublicKey(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
    vaultKeysRepository.save(vaultKeys);
  }

}
