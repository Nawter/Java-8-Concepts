package com.zaid.concepts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


import org.junit.Test;

public class BuiltInInterfacesTest {
  private Predicate<String> predicate;
  private Function<String, Integer> toInteger;
  private Function<String, String> backToString;
  private Supplier<Person> personSupplier;
  private Consumer<Person> greeter;
  private Comparator<Person> comparator;
  private Runnable runnable;
  private Callable<UUID> callable;

  @Test
  public void predicateReturnValidValue() {
    predicate = (s) -> s.length() > 0;
    assertTrue(predicate.test("word"));
    assertFalse(predicate.negate().test("word"));
  }

  @Test
  public void functionReturnValidValue() {
    toInteger = Integer::valueOf;
    backToString = toInteger.andThen(String::valueOf);
    assertEquals("123", backToString.apply("123"));
  }

  @Test
  public void suppliersReturnValidValue() {
    personSupplier = Person::new;
    assertTrue(personSupplier.get() instanceof Person);
  }
  
  @Test
  public void consumersReturnValidValue() {
    greeter = (p) -> System.out.println("Hello, " + p.firstName);
    greeter.accept(new Person("Zaid", "Alissa"));
  }
  
  @Test
  public void comparatorReturnValidValue() {
    comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
    Person p1 = new Person("John", "Doe");
    Person p2 = new Person("Alice", "Wonderland");
    assertTrue(comparator.compare(p1, p2) > 0);             
    assertTrue(comparator.reversed().compare(p1, p2) < 0);
  }
  
  @Test
  public void runnableReturnValidValue() {
    runnable = () -> System.out.println(UUID.randomUUID());
    runnable.run();
  }
  
  @Test
  public void callableReturnValidValue() throws Exception {
    callable = UUID::randomUUID;
    callable.call();
  }

}
