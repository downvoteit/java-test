package com.downvoteit.javatest.entity;

public class Account {
  private final Number id;
  private final String name;
  private Double sum;

  public Account(Number id, String name, Double sum) {
    this.id = id;
    this.name = name;
    this.sum = sum;
  }

  public Number getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getSum() {
    return sum;
  }

  public void setSum(Double sum) {
    this.sum = sum;
  }

  @Override
  public String toString() {
    return "Account{" + "id=" + id + ", name='" + name + '\'' + ", sum=" + sum + '}';
  }
}
