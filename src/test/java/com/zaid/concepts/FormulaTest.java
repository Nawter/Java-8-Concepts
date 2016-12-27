package com.zaid.concepts;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FormulaTest {
  private Formula formula;
 
  @Before
  public void setUp(){
   formula=new ImplFormula();
  }
  
  @Test
  public void FormulaInterfaceMethodsIsOK(){
   assertEquals(100, formula.calculate(100),0); 
   assertEquals(0, formula.sqrt(-23),0);
   assertEquals(0,Formula.positive(-4),0);
  }
}


