package com.zaid.concepts;

public class LambdaInterface {

  // A so called functional interface must contain exactly one abstract method declaration.
  // Receive an Integer and return a String.
  @FunctionalInterface
  public static interface Converter<F, T> {
    T convert(F from);
  }

  static class XCode {
    String startsWith(String s) {
      return String.valueOf(s.charAt(0));
    }
  }

  interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
  }
}
