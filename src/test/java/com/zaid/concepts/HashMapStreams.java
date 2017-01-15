package com.zaid.concepts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class HashMapStreams {

  private Map<Integer, String> map;

  @Before
  public void setup() {
    map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      map.putIfAbsent(i, "val" + i);
    }
  }

  @Test
  public void basicOperationsOnMap() {
    map.forEach((id, val) -> System.out.println(val));
    map.computeIfPresent(3, (num, val) -> val + num);
    assertEquals(map.get(3), "val33");
    map.computeIfPresent(9, (num, val) -> null);
    assertFalse(map.containsKey(9));
    map.computeIfAbsent(23, num -> "val" + num);
    assertTrue(map.containsKey(23));
    map.computeIfAbsent(3, num -> "bam");
    assertEquals(map.get(3), "val33");
  }

  @Test
  public void removeOperationsOnMap() {
    map.remove(3, "val3");
    assertEquals(map.get(3), null);
    map.remove(3, "val33");
    assertEquals(map.get(3), null);    
    assertEquals(map.getOrDefault(42, "Not found"), "Not found");    
  }

  @Test
  public void mergeOperationsOnMap() {
    map.merge(10, "val10", (value,newValue)->value.concat(newValue));
    assertEquals(map.get(10), "val10");    
    map.merge(10, "kitkat", (value,newValue)->value.concat(newValue));
    assertEquals(map.get(10), "val10kitkat");     
  }
}
