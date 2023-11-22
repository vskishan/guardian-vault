package com.example.guardianvault.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class AuthZCodeDTO {

  private String username;

  private String password;

  @JsonProperty(value = "client_id")
  private String clientId;

  @JsonProperty(value = "response_type")
  private String responseType;

  private List<String> scope;

  private String state;

  @JsonProperty(value = "redirect_uri")
  private String redirectURI;

}
