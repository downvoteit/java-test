package com.downvoteit.javatest.service.basic;

import com.downvoteit.javatest.entity.Account;
import com.downvoteit.javatest.service.exceptions.AccountException;

import java.util.ArrayList;
import java.util.List;

public class BasicAlteredUserService implements UserService {
  private static final List<Account> accounts =
      List.of(
          new Account(Integer.MAX_VALUE + 1L, "Anna", 7000D),
          new Account(Integer.MAX_VALUE + 2L, "Katarina", 1300D));

  public static List<Account> getAccounts() {
    return new ArrayList<>(accounts);
  }

  @Override
  public Account getAccountData(Number id) throws AccountException {
    if (id == null || id.longValue() == 0) throw new AccountException("Invalid account id");

    return findAccount(accounts, id);
  }
}
