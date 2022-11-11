package com.example.ecommerce.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    // SELECT * FROM appuser WHERE email = ?
Optional<AppUser> findAppUserByEmail(String email);
}
