package com.downvoteit.javatest;

import com.downvoteit.javatest.service.exceptions.TransactionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationMainTest {
  @Test
  void main_mustNotThrow_PositiveTest() {
    assertDoesNotThrow(() -> ApplicationMain.main(new String[] {"6000D", "7000D"}));
  }

  @ParameterizedTest
  @CsvSource(
      value = {"-8000D:-8000D", "0D:0D", "8000D:8000D"},
      delimiter = ':')
  void main_mustThrow_NegativeTest(String argOne, String argTwo) {
    assertThrows(
        TransactionException.class, () -> ApplicationMain.main(new String[] {argOne, argTwo}));
  }
}
