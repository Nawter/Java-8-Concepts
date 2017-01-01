package com.zaid.concepts;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.zaid.concepts.LambdaInterface.Converter;
import com.zaid.concepts.LambdaInterface.PersonFactory;
import com.zaid.concepts.LambdaInterface.XCode;

public class LambdaInterfaceTest {

  private Converter<String, Integer> converter;
  private Converter<String, Integer> converterMethodReference;
  private Converter<String, String> stringConverter;
  private PersonFactory<Person> personFactory;

  @Before
  public void setup() {
    converter = (from) -> Integer.valueOf(from);
    converterMethodReference = Integer::valueOf;
  }

  @Test
  public void converterReturnValidValue() {
    Integer converted = converter.convert("123");
    assertEquals(123, converted.intValue());
  }

  @Test
  public void converterReturnValidValueWhenUsingMethodReference() {
    Integer converted = converterMethodReference.convert("123");
    assertEquals(123, converted.intValue());
  }

  @Test
  public void XCodeReturnValidValueWhenUsingMethodReference() {
    XCode xcode = new XCode();
    stringConverter = xcode::startsWith;
    String converted = stringConverter.convert("Java");
    assertEquals("J", converted);
  }

  @Test
  public void PersonFactoryReturnValidValueWhenUsingConstructorReference() {
    personFactory = Person::new;
    Person person = personFactory.create("zaid", "alissa");
    assertEquals("zaid", person.getFirstName());
  }
}
