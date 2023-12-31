package com.example.guardianvault.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "authzcode_vault")
@Data
public class AuthZCode {

  @Id
  private String clientId;

  @JsonProperty(value = "authorization_code")
  private String authorizationCode;

  @JsonProperty(value = "redirect_uri")
  private String redirectURI;

  private String state;
}
