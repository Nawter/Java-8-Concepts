package com.zaid.concepts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class StreamTests {
  private List<String> stringCollection;
  private List<String> values;
  
  @Before
  public void setup() {
    stringCollection = new ArrayList<>();
    stringCollection.add("ddd2");
    stringCollection.add("aaa2");
    stringCollection.add("bbb1");
    stringCollection.add("aaa1");
    stringCollection.add("bbb3");
    stringCollection.add("ccc");
    stringCollection.add("bbb2");
    stringCollection.add("ddd1");    
    int max=1000000;
    values = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {
      UUID uuid = UUID.randomUUID();
      values.add(uuid.toString());  
    }
  }

  @Test
  public void filterTest() {
    System.out.print("filterTest: ");
    stringCollection
    .stream()
    .filter((s) -> s.startsWith("a"))       
    .forEach(System.out::print); 
    System.out.println();
  }
  
  @Test
  public void sortedTest() {
    System.out.print("sortedTest: ");
    stringCollection
    .stream()
    .sorted()
    .filter((s) -> s.startsWith("a"))
    .forEach(System.out::print);    
    System.out.println();
  }
  
  @Test
  public void mapTest() {
    System.out.print("mapTest: ");
    stringCollection
    .stream()
    .map(String::toUpperCase)
    .sorted((a,b)->b.compareTo(a))
    .forEach(System.out::print);
    System.out.println();
  }
  
  @Test
  public void matchTest() {
    boolean anyStartsWithA = stringCollection
                             .stream()
                             .anyMatch((s)->s.startsWith("a"));
    assertTrue(anyStartsWithA);
    boolean allStartsWithA = stringCollection
        .stream()
        .allMatch((s)->s.startsWith("a"));
    assertFalse(allStartsWithA);
    boolean nonStartsWithA = stringCollection
        .stream()
        .noneMatch((s)->s.startsWith("z"));
    assertTrue(nonStartsWithA);    
  }
  
  @Test
  public void countTest() {
  long startsWithB = stringCollection
        .stream()
        .filter((s)->s.startsWith("b"))
        .count();
  assertEquals(startsWithB,3);
  }
  
  @Test
  public void reduceTest() {
    System.out.print("reduceTest: ");
  Optional<String> reduced= stringCollection
        .stream()
        .sorted()
        .reduce((s1,s2)->s1+"#"+s2);
  reduced.ifPresent(System.out::println);  
  }
  
  @Test
  public void sequentialTest() {
    long t0 = System.nanoTime();
    long count = values
        .stream()
        .sorted()
        .count();
    assertEquals(count, values.size());
    long t1 = System.nanoTime();
    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
    System.out.println(String.format("sequential sort took: %d ms", millis));
  }
  
  @Test
  public void paralellTest() {
    long t0 = System.nanoTime();
    long count = values
        .parallelStream()
        .sorted()
        .count();
    assertEquals(count, values.size());
    long t1 = System.nanoTime();
    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
    System.out.println(String.format("paralell sort took: %d ms", millis));
  }
}
