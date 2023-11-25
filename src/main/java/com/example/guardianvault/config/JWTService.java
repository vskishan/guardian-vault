package com.example.guardianvault.config;


import com.example.guardianvault.entity.Client;
import com.example.guardianvault.repository.ClientRepository;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTService {

  @Autowired
  ClientRepository clientRepository;

  public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
  public String generateToken(String clientId) {
    Map<String, Object> claims = new HashMap<>();
    var client = clientRepository.findByClientId(clientId);
    setClaims(client, claims);
    return createToken(claims);
  }

  private String createToken(Map<String, Object> claims) {
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
  }

  private Key getSignKey() {
    byte[] keyBytes= Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Map<String, Object> setClaims(Client client, Map<String, Object> claims){
    claims.put("scp", new ArrayList<>(client.getScopes()));
    claims.put("cid", client.getClientId());
    return claims;
  }

}
