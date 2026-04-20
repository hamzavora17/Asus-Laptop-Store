package com.lptstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lptstore.entity.user;

public interface UserRepository extends JpaRepository<user, Long> {
    user findByEmailAndPassword(String email, String password);
    java.util.List<user> findByEmail(String email);
}