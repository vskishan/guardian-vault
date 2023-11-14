package com.example.guardianvault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuthSecurityConfig {

  @Bean
  SecurityFilterChain securityConfig(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.authorizeHttpRequests(request -> {
          request.requestMatchers("/register").permitAll();
    })
    .csrf(csrf -> {
      csrf.ignoringRequestMatchers("/register");
    })
    .httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
  }

}
