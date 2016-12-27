package com.zaid.concepts;

public class ImplFormula implements Formula {

  @Override
  public double calculate(int a) {
    return sqrt(a * 100);
  }
}
