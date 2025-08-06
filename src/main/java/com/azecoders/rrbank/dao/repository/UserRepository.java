package com.azecoders.rrbank.dao.repository;

import com.azecoders.rrbank.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity>findByEmail(String mail);
    boolean existsByEmail (String email);
}
