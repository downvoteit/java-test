package com.downvoteit.javatest;

import com.downvoteit.javatest.service.basic.BasicAlteredUserService;
import com.downvoteit.javatest.service.basic.BasicDefaultUserService;
import com.downvoteit.javatest.service.basic.UserService;
import com.downvoteit.javatest.service.exceptions.AccountException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
  @Nested
  class DefaultImpl {
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getAccountData_mustNotThrow_PositiveTest(Integer id) {
      UserService userService = new BasicDefaultUserService();

      assertDoesNotThrow(() -> userService.getAccountData(id));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void getAccountData_mustThrow_NegativeTest(Integer id) {
      UserService userService = new BasicDefaultUserService();

      Exception exception =
          assertThrows(AccountException.class, () -> userService.getAccountData(id));

      assertEquals("Account not found", exception.getMessage());
    }
  }

  @Nested
  class AlteredImpl {
    @ParameterizedTest
    @ValueSource(longs = {1, 2})
    void getAccountData_mustNotThrow_PositiveTest(Long id) {
      UserService userService = new BasicAlteredUserService();

      assertDoesNotThrow(() -> userService.getAccountData(Integer.MAX_VALUE + id));
    }

    @ParameterizedTest
    @ValueSource(longs = {4, 5, 6})
    void getAccountData_mustNotThrow_NegativeTest(Long id) {
      UserService userService = new BasicDefaultUserService();

      Exception exception =
          assertThrows(AccountException.class, () -> userService.getAccountData(id));

      assertEquals("Account not found", exception.getMessage());
    }
  }
}
