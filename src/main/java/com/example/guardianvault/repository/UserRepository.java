package com.example.guardianvault.repository;

import com.example.guardianvault.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  public User findByUsername(String userName);

}
