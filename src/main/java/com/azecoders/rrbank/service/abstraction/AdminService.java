package com.azecoders.rrbank.service.abstraction;


import com.azecoders.rrbank.model.response.AccountResponse;

import java.util.List;

public interface AdminService {
    List<AccountResponse> allPendingAccounts();

    String unblockAccount(String nationalID);

    String approveAccountOrder(Long id);

    String rejectAccountOrder(Long id);

    void approveCardOrder(Long id);


}
