package com.azecoders.rrbank.service.abstraction;

<<<<<<< HEAD
=======
import com.azecoders.rrbank.dao.entity.BankAccountEntity;
>>>>>>> b8be9f1a352aeed605db1b55bd4251358224649d
import com.azecoders.rrbank.model.response.AccountResponse;

import java.util.List;

public interface AdminService {
    List<AccountResponse> allPendingAccounts();

    String unblockAccount(String nationalID);

    String approveAccountOrder(Long id);

    String rejectAccountOrder(Long id);

    void approveCardOrder(Long id);


}
