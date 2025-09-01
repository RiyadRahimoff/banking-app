package com.azecoders.rrbank.dao.repository;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<BankAccountEntity, Long> {
    List<BankAccountEntity> findAllByAccountStatus(OrderStatus status);
}
