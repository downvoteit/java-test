package com.downvoteit.javatest.service.factory;

import com.downvoteit.javatest.entity.Account;
import com.downvoteit.javatest.service.basic.BasicDefaultUserService;
import com.downvoteit.javatest.service.basic.BasicTransactionService;
import com.downvoteit.javatest.service.basic.TransactionService;
import com.downvoteit.javatest.service.basic.UserService;
import com.downvoteit.javatest.service.exceptions.AccountException;
import com.downvoteit.javatest.service.exceptions.TransactionException;

public abstract class UserAccountManagementFactory {
  protected TransactionService transactionService;
  protected UserService userService;

  public UserAccountManagementFactory create() {
    transactionService = new BasicTransactionService();
    userService = new BasicDefaultUserService();

    return this;
  }

  public Number transferFunds(Number debitId, Number creditId, Double sum)
      throws AccountException, TransactionException {
    Account debitAccount = userService.getAccountData(debitId);
    Account creditAccount = userService.getAccountData(creditId);

    return transactionService.createTransaction(debitAccount, creditAccount, sum);
  }
}
