package com.azecoders.rrbank.dao.repository;

import com.azecoders.rrbank.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity,Long> {
}
