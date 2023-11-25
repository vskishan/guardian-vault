package com.example.guardianvault.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import lombok.Data;

@Data
public class ClientDTO {

  @JsonProperty(value = "client_id")
  private String clientId;

  private List<String> scopes;

  @JsonProperty(value = "client_secret", access = Access.READ_ONLY)
  @JsonInclude(value = Include.NON_NULL)
  private String clientSecret;
}
