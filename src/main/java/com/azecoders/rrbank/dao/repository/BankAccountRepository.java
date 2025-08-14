package com.azecoders.rrbank.dao.repository;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity,Long> {
}
