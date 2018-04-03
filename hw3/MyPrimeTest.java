package edu.upenn.cis573.math;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyPrimeTest {
	
	private int n;
	private int np;
	
	@Before
	public void setUp() throws Exception {
		n = 0;
		np = 0;
	}

	@Test
	public void testNextPrimeNBelowZero() {
		n = -2;
		try {
			np = MyPrime.nextPrime(n);
			fail("expecting IllegalArgumentException");
		} catch (IllegalArgumentException ex) {
		}
	}
	
	@Test
	public void testNextPrimeNEqualsOne() {
		n = 1;
		np = 2;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeNEqualsTwo() {
		n = 2;
		np = 2;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeNIsPrime() {
		n = 3;
		np = 3;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeRemEqualsZero() {
		n = 9;
		np = 11;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeRemEqualsOneNPrime() {
		n = 25;
		np = 29;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeRemEqualsZeroNPlusFour() {
		n = 183;
		np = 191;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeRemEqualsZeroNPlusTwo() {
		n = 33;
		np = 37;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeRemEqualsOneNPlusTwo() {
		n = 205;
		np = 211;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeRemEqualsOneNPlusFour() {
		n = 295;
		np = 307;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void testNextPrimeRemEqualsTwo() {
		n = 35;
		np = 37;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void tesIsPrimeTEqualsTwo() {
		n = 22000;
		np = 22003;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void tesIsPrimeTEqualsThree() {
		n = 2511211;
		np = 2511247;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void tesIsPrimeTEqualsThree_1() {
		n = 3464555;
		np = 3464567;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void tesIsPrimeTEqualsFour() {
		n = 25326020;
		np = 25326023;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void tesIsPrimeTEqualsFour_NminusNot() {
		n = 136461518;
		np = 136461583;
		assertEquals(MyPrime.nextPrime(n),np);
	}
	
	@Test
	public void tesIsPrimeTEqualsFour_JPP() {
		n = 456539;
		np = 456539;
		assertEquals(MyPrime.nextPrime(n),np);
	}
}