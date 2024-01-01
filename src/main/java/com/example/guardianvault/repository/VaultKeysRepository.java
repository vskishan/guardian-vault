package com.example.guardianvault.repository;

import com.example.guardianvault.entity.VaultKeys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaultKeysRepository extends JpaRepository<VaultKeys, Integer> {

  VaultKeys findTopByOrderByIdDesc();
}
