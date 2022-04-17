package com.downvoteit.javatest.service.basic;

import com.downvoteit.javatest.entity.Account;
import com.downvoteit.javatest.service.exceptions.AccountException;
import com.downvoteit.javatest.service.exceptions.TransactionException;

import java.util.Random;

public class BasicTransactionService implements TransactionService {
  @Override
  public void validate(Account debit, Account credit, Double sum)
      throws AccountException, TransactionException {
    if (debit == null) throw new AccountException("Debit account not found");
    if (credit == null) throw new AccountException("Credit account not found");

    if (sum <= 0)
      throw new TransactionException("Invalid transaction sum, sum cannot be equal or less than 0");
    if (debit.getSum() - sum < 0)
      throw new TransactionException("Invalid transaction sum for account " + debit.getName());
    if (credit.getSum() < 0)
      throw new TransactionException("Invalid transaction sum for account " + credit.getName());
  }

  @Override
  public synchronized Integer createTransaction(Account debit, Account credit, Double sum)
      throws AccountException, TransactionException {
    validate(debit, credit, sum);

    Double debitTemp = debit.getSum();
    Double creditTemp = credit.getSum();

    debit.setSum(debitTemp - sum);
    credit.setSum(creditTemp + sum);

    return new Random().nextInt(10);
  }
}
