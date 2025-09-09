package com.azecoders.rrbank.dao.repository;

import com.azecoders.rrbank.dao.entity.CardEntity;
import com.azecoders.rrbank.model.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity,Long> {
    boolean existsByAccount_User_IdAndCardType(Long userId, CardType cardType);
    Optional<CardEntity> findByCardNumber(String cardNumber);
}
