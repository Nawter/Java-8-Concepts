package com.zaid.concepts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zaid.concepts.LambdaInterface.Converter;

public class LambdaScopeTest {
  private static int outerStaticNum;
  private int outerNum;

  @Test
  public void checkAccessingLocalVariables() {
    final int finalNum = 1;
    int num = 2;
    Converter<Integer, String> stringConverterFinal = (from) -> String.valueOf(from + finalNum);
    Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
    assertEquals("3", stringConverterFinal.convert(2));
    assertEquals("4", stringConverter.convert(2));
  }

  @Test
  public void checkAccessingFields() {
    Converter<Integer, String> stringConverterStatic = (from) -> {
      outerNum = 23;
      return String.valueOf(from+outerNum);
    };
    Converter<Integer, String> stringConverter = (from) -> {
      outerStaticNum = 72;
      return String.valueOf(from+outerStaticNum);
    };
    assertEquals("25", stringConverterStatic.convert(2));
    assertEquals("74", stringConverter.convert(2));  
  }
  
  
}
