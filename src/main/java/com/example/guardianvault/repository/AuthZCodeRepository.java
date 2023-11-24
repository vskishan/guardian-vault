package com.example.guardianvault.repository;

import com.example.guardianvault.entity.AuthZCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthZCodeRepository extends JpaRepository<AuthZCode, String> {

}
