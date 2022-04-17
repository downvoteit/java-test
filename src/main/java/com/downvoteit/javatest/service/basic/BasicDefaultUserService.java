package com.downvoteit.javatest.service.basic;

import com.downvoteit.javatest.entity.Account;
import com.downvoteit.javatest.service.exceptions.AccountException;

import java.util.ArrayList;
import java.util.List;

public class BasicDefaultUserService implements UserService {
  private static final List<Account> accounts =
      List.of(new Account(1, "Sam", 7000D), new Account(2, "Eric", 1300D));

  public static List<Account> getAccounts() {
    return new ArrayList<>(accounts);
  }

  @Override
  public Account getAccountData(Number id) throws AccountException {
    if (id == null || id.intValue() == 0) throw new AccountException("Invalid account id");

    return findAccount(accounts, id);
  }
}
