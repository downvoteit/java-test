package com.downvoteit.javatest;

import com.downvoteit.javatest.service.basic.BasicAlteredUserService;
import com.downvoteit.javatest.service.basic.BasicDefaultUserService;
import com.downvoteit.javatest.service.exceptions.AccountException;
import com.downvoteit.javatest.service.exceptions.TransactionException;
import com.downvoteit.javatest.service.factory.BasicUserAccountManagementAlteredFactory;
import com.downvoteit.javatest.service.factory.BasicUserAccountManagementDefaultFactory;
import com.downvoteit.javatest.service.factory.UserAccountManagementFactory;

public class ApplicationMain {
  public static void main(String[] args) throws AccountException, TransactionException {
    ApplicationMain applicationMain = new ApplicationMain();

    applicationMain.runDefaultImpl(Double.valueOf(args[0]));
    applicationMain.runAlteredImpl(Double.valueOf(args[1]));
  }

  public void runDefaultImpl(Double sum) throws AccountException, TransactionException {
    UserAccountManagementFactory managementFactory =
        new BasicUserAccountManagementDefaultFactory().create();

    Number debitId = 1, creditId = 2;

    System.out.println(BasicDefaultUserService.getAccounts());
    run(managementFactory, debitId, creditId, sum);
    System.out.println(BasicDefaultUserService.getAccounts());
  }

  public void runAlteredImpl(Double sum) throws AccountException, TransactionException {
    UserAccountManagementFactory managementFactory =
        new BasicUserAccountManagementAlteredFactory().create();

    Number debitId = Integer.MAX_VALUE + 1L, creditId = Integer.MAX_VALUE + 2L;

    System.out.println(BasicAlteredUserService.getAccounts());
    run(managementFactory, debitId, creditId, sum);
    System.out.println(BasicAlteredUserService.getAccounts());
  }

  private void run(
      UserAccountManagementFactory userAccountManagementFactory,
      Number debitId,
      Number creditId,
      Double sum)
      throws AccountException, TransactionException {
    userAccountManagementFactory.transferFunds(debitId, creditId, sum);
  }
}
