package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;

import java.util.List;

public interface AdminService {
    List<BankAccountEntity> allPendingAccounts();
    String unblockAccount(String nationalID);

}
