package com.example.guardianvault.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "client_vault")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

  @Id
  @JsonProperty(value = "client_id")
  private String clientId;

  @JsonProperty(value = "client_secret")
  @NonNull
  private String clientSecret;

  @JsonProperty
  private List<String> scopes;

}
