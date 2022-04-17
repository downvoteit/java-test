package com.downvoteit.javatest.service.basic;

import com.downvoteit.javatest.entity.Account;
import com.downvoteit.javatest.service.exceptions.AccountException;
import com.downvoteit.javatest.service.exceptions.TransactionException;

public interface TransactionService {
  void validate(Account debit, Account credit, Double sum)
      throws AccountException, TransactionException;

  Number createTransaction(Account debit, Account credit, Double sum)
      throws TransactionException, AccountException;
}
