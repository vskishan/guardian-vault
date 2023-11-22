package com.example.guardianvault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuthSecurityConfig {

  @Bean
  SecurityFilterChain securityConfig(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.httpBasic(Customizer.withDefaults())
    .authorizeHttpRequests(request -> {
      request.requestMatchers("/users/register", "/clients/register", "/verify-details").permitAll()
            .requestMatchers("/users/*","/clients/*/details").authenticated();
    })
    .csrf(csrf -> {
      csrf.ignoringRequestMatchers("/users/register", "/clients/register", "/verify-details");
    });
    return httpSecurity.build();
  }

}
