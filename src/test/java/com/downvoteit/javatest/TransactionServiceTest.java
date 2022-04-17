package com.downvoteit.javatest;

import com.downvoteit.javatest.entity.Account;
import com.downvoteit.javatest.service.basic.BasicTransactionService;
import com.downvoteit.javatest.service.exceptions.AccountException;
import com.downvoteit.javatest.service.exceptions.TransactionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
  @ParameterizedTest
  @CsvSource(
      value = {
        "T,1,A,1000D,2,B,0D",
        "T,1,A,100D,2,B,0D",
        "F,1,A,0D,2,B,1000D",
        "F,1,A,100D,2,B,-1000D"
      })
  void validate_mayThrow_CombinedTest(
      String flag, @AggregateWith(AccountAggregator.class) List<Account> accounts) {
    BasicTransactionService transactionService = new BasicTransactionService();

    Double sum = 100D;
    Account debitAccount = accounts.get(0);
    Account creditAccount = accounts.get(1);

    if (flag.equals("T"))
      assertDoesNotThrow(() -> transactionService.validate(debitAccount, creditAccount, sum));
    else
      assertThrows(
          TransactionException.class,
          () -> transactionService.validate(debitAccount, creditAccount, sum));
  }

  @Test
  void validate_mustThrow_NegativeTest() {
    BasicTransactionService transactionService = new BasicTransactionService();

    Account debitAccount = mock(Account.class);
    Account creditAccount = mock(Account.class);

    Exception exception =
        assertThrows(
            TransactionException.class,
            () -> transactionService.validate(debitAccount, creditAccount, 100D));

    assertEquals("Invalid transaction sum for account null", exception.getMessage());
  }

  @ParameterizedTest
  @ValueSource(doubles = {100, 200, 300})
  void createTransaction_mustNotBeNull_PositiveTest(Double sum)
      throws AccountException, TransactionException {
    BasicTransactionService transactionService = spy(new BasicTransactionService());

    Account debitAccount = mock(Account.class);
    Account creditAccount = mock(Account.class);

    doNothing().when(transactionService).validate(debitAccount, creditAccount, sum);
    Integer transactionId = transactionService.createTransaction(debitAccount, creditAccount, sum);

    assertNotNull(transactionId);

    verify(transactionService, times(1)).validate(debitAccount, creditAccount, sum);
  }

  @ParameterizedTest
  @ValueSource(doubles = {-100, 0, 100})
  void createTransaction_mustThrow_VerifyOnce_NegativeTest(Double sum)
      throws AccountException, TransactionException {
    BasicTransactionService transactionService = spy(new BasicTransactionService());

    Account debitAccount = mock(Account.class);
    Account creditAccount = mock(Account.class);

    String message = "Invalid transaction sum";

    doThrow(new TransactionException(message))
        .when(transactionService)
        .validate(debitAccount, creditAccount, sum);

    Exception exception =
        assertThrows(
            TransactionException.class,
            () -> transactionService.createTransaction(debitAccount, creditAccount, sum));

    assertEquals(message, exception.getMessage());

    verify(transactionService, times(1)).validate(debitAccount, creditAccount, sum);
  }
}
