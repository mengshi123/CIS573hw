package edu.upenn.cis573.math;

import static org.junit.Assert.*;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.fraction.Fraction;
import org.junit.Before;
import org.junit.Test;

public class MyFractionsTest {
	private Fraction f1;
	private Fraction f2;
	private Fraction f;
	@Before
	public void setUp() throws Exception {
		f1 = null;
		f2 = null;
		f = null;
	}

	@Test
	public void testAddSub_NullF1() {
		 f1 = Fraction.getReducedFraction(3, 5);
	     f = Fraction.getReducedFraction(3, 5);
	     try {
		    	MyFractions.addSub(f1, f2, true); // should overflow
	            fail("expecting NullArgumentException but got: " + f.toString());
	        } catch (NullArgumentException ex) {}
		 try {
		    	MyFractions.addSub(f2, f2, false); // should overflow
	            fail("expecting NullArgumentException but got: " + f.toString());
	        } catch (NullArgumentException ex) {}
	}
	
	@Test
	public void testAddSub_f1Zero() {
		f1 = Fraction.getReducedFraction(0, 7);
	    f2 = Fraction.getReducedFraction(4, 5);
	    f = Fraction.getReducedFraction(-4, 5);
	    assertEquals(MyFractions.addSub(f1, f2, true), f2);
	    assertEquals(MyFractions.addSub(f2, f1, true), f2);
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	    assertEquals(MyFractions.addSub(f2, f1, false), f2);
	}
	
	@Test
	public void testAddSub_DOneEqualsOne_One() {
		 f1 = Fraction.getReducedFraction(3, 5);
	     f2 = Fraction.getReducedFraction(1, 5);
	     f = Fraction.getReducedFraction(4, 5);
         assertEquals(MyFractions.addSub(f1, f2, true), f);
         f = Fraction.getReducedFraction(2, 5);
         assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneEqualsOne_Two() {
		f1 = Fraction.getReducedFraction(3, 7);
	    f2 = Fraction.getReducedFraction(4, 7);
	    f = Fraction.ONE;
	    assertEquals(MyFractions.addSub(f1, f2, true), f);
	    f1 = Fraction.getReducedFraction(11, 7);
	    f2 = Fraction.getReducedFraction(4, 7);
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneEqualsOne_Three() {
		f1 = Fraction.getReducedFraction(3, 7);
	    f2 = Fraction.getReducedFraction(3, 7);
	    f = Fraction.getReducedFraction(6, 7);
	    assertEquals(MyFractions.addSub(f1, f2, true), f);
	    f = Fraction.ZERO;
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneEqualsOne_Four() {
		f1 = Fraction.getReducedFraction(Integer.MAX_VALUE - 1, 1);
	    f2 = Fraction.ONE;
	    f = Fraction.getReducedFraction(Integer.MAX_VALUE, 1);
	    assertEquals(MyFractions.addSub(f1, f2, true), f);
	    f1 = Fraction.getReducedFraction(Integer.MIN_VALUE, 1);
	    f2 = Fraction.ONE.negate();
	    f = Fraction.getReducedFraction(Integer.MIN_VALUE + 1, 1);
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneNotEqualsOne_One() {
		f1 = Fraction.getReducedFraction(3, 7);
	    f2 = Fraction.getReducedFraction(4, 5);
	    f = Fraction.getReducedFraction(43, 35);
	    assertEquals(MyFractions.addSub(f1, f2, true), f);
	    f = Fraction.getReducedFraction(-13, 35);
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneNotEqualsOne_Two() {
		f1 = Fraction.getReducedFraction(1, 10);
	    f2 = Fraction.getReducedFraction(4, 15);
	    f = Fraction.getReducedFraction(11, 30);
	    assertEquals(MyFractions.addSub(f1, f2, true), f);
	    f = Fraction.getReducedFraction(-1, 6);
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneNotEqualsOne_Three() {
		f1 = Fraction.getReducedFraction(-1, 5*7*2*2*3);
	    f2 = Fraction.getReducedFraction(-2, 7*7*2*3);
	    f = Fraction.getReducedFraction(-7 - 2*5*2, 7*7*5*2*2*3);
	    assertEquals(MyFractions.addSub(f1, f2, true), f);
	    f = Fraction.getReducedFraction(-7 + 2*5*2, 7*7*5*2*2*3);
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneNotEqualsOne_Four() { 
		f1 = Fraction.getReducedFraction(1,32768*3);
	    f2 = Fraction.getReducedFraction(1,59049);
	    f = Fraction.getReducedFraction(52451, 1934917632);
	    assertEquals(MyFractions.addSub(f1, f2, true), f);
	    f = Fraction.getReducedFraction(-13085, 1934917632);
	    assertEquals(MyFractions.addSub(f1, f2, false), f);
	}
	
	@Test
	public void testAddSub_DOneNotEqualsOne_Overflow() {
		f1 = Fraction.getReducedFraction(33333, 555555);
	    f2 = Fraction.getReducedFraction(1212121, 7878787);
	    try {
	    	f = MyFractions.addSub(f1, f2, true); // should overflow
            fail("expecting ArithmeticException but got: " + f.toString());
        } catch (MathArithmeticException ex) {}
	    try {
	    	f = MyFractions.addSub(f1, f2, true); // should overflow
            fail("expecting ArithmeticException but got: " + f.toString());
        } catch (MathArithmeticException ex) {}
	}
	
	@Test
	public void testAddSub_DOneNotEqualsOne_Overflow1() {
		f1 = Fraction.getReducedFraction(Integer.MIN_VALUE, 7);
	    f2 = Fraction.getReducedFraction(-1, 8);
	    try {
	    	f = MyFractions.addSub(f1, f2, true); // should overflow
            fail("expecting ArithmeticException but got: " + f.toString());
        } catch (MathArithmeticException ex) {}
	    f1 = Fraction.getReducedFraction(Integer.MAX_VALUE, 7);
	    f2 = Fraction.getReducedFraction(-1, 8);
	    try {
	    	f = MyFractions.addSub(f1, f2, true); // should overflow
            fail("expecting ArithmeticException but got: " + f.toString());
        } catch (MathArithmeticException ex) {}
	}
	
	@Test
	public void testAddSub_DOneNotEqualsOne_Overflow2() {
		f1 = Fraction.getReducedFraction(1021*1024*1024, 81);
	    f2 = Fraction.getReducedFraction(1021*1024*1024, 125);
	    try {
	    	f = MyFractions.addSub(f1, f2, true); // should overflow
            fail("expecting ArithmeticException but got: " + f.toString());
        } catch (MathArithmeticException ex) {}
	    try {
	    	f = MyFractions.addSub(f1, f2, false); // should overflow
            fail("expecting ArithmeticException but got: " + f.toString());
        } catch (MathArithmeticException ex) {}
	}      
}
