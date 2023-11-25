package com.example.guardianvault.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenDTO {

  @JsonProperty(value = "client_id")
  private String clientId;

  @JsonProperty(value = "client_secret")
  private String clientSecret;

  @JsonProperty(value = "grant_type")
  private String grantType;

  private String code;

}
