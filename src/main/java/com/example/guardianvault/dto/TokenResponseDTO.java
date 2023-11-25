package com.example.guardianvault.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenResponseDTO {

  @JsonProperty
  private String token;

}
