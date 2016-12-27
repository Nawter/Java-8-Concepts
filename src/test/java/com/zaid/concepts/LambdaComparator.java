package com.zaid.concepts;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LambdaComparator {

  private List<String> names;
  private List<String> namesSortExpected;
  private List<String> namesReverseExpected;

  @Before
  public void setup() {
    names = Arrays.asList("nawal", "muter", "alhareth", "almuala", "safa");
    namesSortExpected = Arrays.asList("alhareth", "almuala", "muter", "nawal", "safa");
    namesReverseExpected = Arrays.asList("safa", "nawal", "muter", "almuala", "alhareth");
  }

  @Test
  public void checkJava7Comparator() {
    Collections.sort(names, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    });
    assertEquals(names, namesSortExpected);
  }

  @Test
  public void checkJava8ComparatorV1() {
    Collections.sort(names, (String a, String b) -> {
      return a.compareTo(b);
    });
    assertEquals(names, namesSortExpected);
  }

  @Test
  public void checkJava8ComparatorV2() {
    Collections.sort(names, (String a, String b) -> a.compareTo(b));
    assertEquals(names, namesSortExpected);
  }

  @Test
  public void checkJava8ComparatorV3() {
    Collections.sort(names, (a, b) -> a.compareTo(b));
    assertEquals(names, namesSortExpected);
  }
  
  @Test
  public void checkJava8ComparatorReverseOrder() {
    names.sort(Collections.reverseOrder());
    assertEquals(names, namesReverseExpected);
  }


}
