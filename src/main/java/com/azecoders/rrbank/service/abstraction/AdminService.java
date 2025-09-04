package com.azecoders.rrbank.service.abstraction;

import com.azecoders.rrbank.dao.entity.BankAccountEntity;
import com.azecoders.rrbank.model.response.AccountResponse;

import java.util.List;

public interface AdminService {
    List<AccountResponse> allPendingAccounts();

    String unblockAccount(String nationalID);

    String acceptAccountOrder(Long id);

    String rejectAccountOrder(Long id);

}
