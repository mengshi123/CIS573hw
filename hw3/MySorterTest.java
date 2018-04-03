package edu.upenn.cis573.math;

import static org.junit.Assert.*;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.junit.Before;
import org.junit.Test;

import edu.upenn.cis573.math.MySorter.OrderDirection;

public class MySorterTest {

	private double[] x;
	private OrderDirection dir;
	private double[][] yList;
	
	@Before
	public void setUp() throws Exception { 
	}
	@Test
	public void testXNull() {
		x = null;
		dir = OrderDirection.INCREASING;
		yList = new double[][] {{1,2},{2,3}};	
		try {
			MySorter.sortInPlace(x, dir, yList);
			fail("X cannot be null");
			}
			catch (NullArgumentException e) {
			}
			catch (Throwable t) {
			fail("Other unexpected exception");
			}	
	}
	
	@Test
	public void testYNull() {
		x = new double[] {1,2};
		dir = OrderDirection.INCREASING;
		yList = new double[][] {null,{2,3}};	
		try {
			MySorter.sortInPlace(x, dir, yList);
			fail("Y cannot have null ");
			}
			catch (NullArgumentException e) {
			}
			catch (Throwable t) {
			fail("Other unexpected exception");
			}	
	}
	
	@Test
	public void testXYLength() {
		x = new double[] {1,2};
		dir = OrderDirection.INCREASING;
		yList = new double[][] {{1},{2,3}};	
		try {
			MySorter.sortInPlace(x, dir, yList);
			fail("Length are not Equal");
			}
			catch (DimensionMismatchException e) {
			}
			catch (Throwable t) {
			fail("Other unexpected exception");
			}	
	}
	
	@Test
	public void testIncreasing() {
		x = new double[] {1,3,2};
		dir = OrderDirection.INCREASING;
		yList = new double[][] {{1,2,3},{6,5,4}};
		double[] xExpected = {1,2,3};
		double[][] yExpected = {{1,3,2},{6,4,5}};
		MySorter.sortInPlace(x, dir, yList);
		for(int i = 0; i < x.length; i++){
			assertTrue(x[i] == xExpected[i]);
		}
		for(int i = 0; i < yList.length; i++){
			for(int j = 0; j < yList[i].length; j++){
				assertTrue(yList[i][j] == yExpected[i][j]);
				
			}
		}
	}
	
	@Test
	public void testDecreasing() {
		x = new double[] {1,3,2};
		dir = OrderDirection.DECREASING;
		yList = new double[][] {{1,2,3},{6,5,4}};
		double[] xExpected = {3,2,1};
		double[][] yExpected = {{2,3,1},{5,4,6}};
		MySorter.sortInPlace(x, dir, yList);
		for(int i = 0; i < x.length; i++){
			assertTrue(x[i] == xExpected[i]);
		}
		for(int i = 0; i < yList.length; i++){
			for(int j = 0; j < yList[i].length; j++){
				assertTrue(yList[i][j] == yExpected[i][j]);
				
			}
		}
	}

}
