package com.downvoteit.javatest.service.basic;

import com.downvoteit.javatest.entity.Account;
import com.downvoteit.javatest.service.exceptions.AccountException;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
  Account getAccountData(Number id) throws AccountException;

  default Account findAccount(List<Account> accounts, Number id) throws AccountException {
    for (Account account : accounts)
      if (account.getId().equals(id)) {
        return account;
      }

    throw new AccountException("Account not found");
  }
}
