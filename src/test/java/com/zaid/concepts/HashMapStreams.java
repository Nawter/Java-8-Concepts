package com.zaid.concepts;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class HashMapStreams {

  private Map<Integer, String> map;

  @Before
  public void setup() {
    map = new HashMap<>();
    for(int i=0;i<10;i++){
      map.putIfAbsent(i, "vale"+i);
    }
  }
  
  @Test
  public void basicOperationsOnMap(){
    map.forEach((id,val)->System.out.println(val));
    map.computeIfPresent(3, (num,val)->val+num);
    
    assertEquals(map.get(3).toString(),"val33");
  }
  
  
}
