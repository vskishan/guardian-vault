package com.example.guardianvault.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ClientDTO {

  @JsonProperty(value = "client_id")
  private String clientId;

  private List<String> scopes;

}
