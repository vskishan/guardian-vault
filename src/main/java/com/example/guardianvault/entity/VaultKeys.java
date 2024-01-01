package com.example.guardianvault.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.security.Key;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vault_keys")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaultKeys {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="MySequenceGenerator")
  private Integer id;

  private String publicKey;

  private String privateKey;
}
