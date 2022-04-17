package com.downvoteit.javatest;

import com.downvoteit.javatest.entity.Account;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.util.List;

class AccountAggregator implements ArgumentsAggregator {
  @Override
  public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
      throws ArgumentsAggregationException {
    Account debit =
        new Account(
            Long.valueOf((String) accessor.get(1)), accessor.getString(2), accessor.getDouble(3));
    Account credit =
        new Account(
            Long.valueOf((String) accessor.get(4)), accessor.getString(5), accessor.getDouble(6));

    return List.of(debit, credit);
  }
}
