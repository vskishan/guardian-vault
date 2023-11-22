package com.example.guardianvault.repository;

import com.example.guardianvault.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUsername(String userName);

}
