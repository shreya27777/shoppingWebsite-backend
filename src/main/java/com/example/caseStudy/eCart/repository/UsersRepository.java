package com.example.caseStudy.eCart.repository;

import com.example.caseStudy.eCart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Optional<Users> findByEmail(String email);
}
