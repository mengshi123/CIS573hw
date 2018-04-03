package edu.upenn.cis573;

import static org.junit.Assert.*;

import org.junit.Test;

public class HealthIndicatorTest {

	@Test
	public void testInvalid() {
		//test case(age < 20)
		HealthIndicator.WeightStatus invaladAge = HealthIndicator.calculateWeightStatus(6, 4.0, 152.0, 19);
		assertEquals(invaladAge, HealthIndicator.WeightStatus.INVALID);
	}
	
	@Test 
	public void testUnderweightBelowPounds() {
		//test case(BMI < 18.5 when weight is lighter)
		HealthIndicator.WeightStatus Underweight = HealthIndicator.calculateWeightStatus(6, 4.0, 151.9, 21);
		assertEquals(Underweight, HealthIndicator.WeightStatus.UNDERWEIGHT);
	}	
	
	@Test
	public void testUnderweightAboveHeight() {
		//test case(BMI < 18.5 when height is taller)
		HealthIndicator.WeightStatus Underweight1 = HealthIndicator.calculateWeightStatus(6, 4.1, 152.0, 21);
		assertEquals(Underweight1, HealthIndicator.WeightStatus.UNDERWEIGHT);
	}
	
	@Test
	public void testHealthyEqual() {
		//test case(BMI = 18.5)
		HealthIndicator.WeightStatus healthyEqual = HealthIndicator.calculateWeightStatus(6, 4.0, 152.0, 21);
		assertEquals(healthyEqual, HealthIndicator.WeightStatus.HEALTHY);
	}
	
	@Test
	public void testHealthyabovePounds() {
		//test case(BMI > 18.5 when weight is heavier)
		HealthIndicator.WeightStatus healthyAbove = HealthIndicator.calculateWeightStatus(6, 4.0, 152.1, 21);
		assertEquals(healthyAbove, HealthIndicator.WeightStatus.HEALTHY);
	}
	
	@Test
	public void testHealthyBelowHeight() {
		//test case(BMI > 18.5 when height is shorter)
	    HealthIndicator.WeightStatus healthyAbove1 = HealthIndicator.calculateWeightStatus(6, 3.9, 152.0, 21);
		assertEquals(healthyAbove1, HealthIndicator.WeightStatus.HEALTHY);
	}
	
	@Test
	public void testHealthyBelowPounds() {
		//test case(BMI < 25 when weight is lighter)
		HealthIndicator.WeightStatus healthyBelow = HealthIndicator.calculateWeightStatus(4, 8.24, 112.47, 21);
		assertEquals(healthyBelow, HealthIndicator.WeightStatus.HEALTHY);
	}
	
	@Test
	public void testHealthyAboveHeight() {
		//test case(BMI < 25 when height is taller)
		HealthIndicator.WeightStatus healthyBelow1 = HealthIndicator.calculateWeightStatus(4, 8.25, 112.48, 21);
		assertEquals(healthyBelow1, HealthIndicator.WeightStatus.HEALTHY);
	}
	
	@Test
	public void testOverweightEqual() {
		//test case(BMI = 25)
		HealthIndicator.WeightStatus overweightEqual = HealthIndicator.calculateWeightStatus(4, 8.24, 112.48, 21);
		assertEquals(overweightEqual, HealthIndicator.WeightStatus.OVERWEIGHT);
	}
	
	@Test
	public void testOverweightAbovePounds() {
		//test case(BMI > 25 when weight is heavier)
		HealthIndicator.WeightStatus overweightAbove = HealthIndicator.calculateWeightStatus(4, 8.24, 112.49, 21);
		assertEquals(overweightAbove,HealthIndicator.WeightStatus.OVERWEIGHT);
	}
	
	@Test
	public void testOverweightBelowHeight() {
		//test case(BMI > 25 when height is shorter)
		HealthIndicator.WeightStatus overweightAbove1 = HealthIndicator.calculateWeightStatus(4, 8.23, 112.48, 21);
		assertEquals(overweightAbove1,HealthIndicator.WeightStatus.OVERWEIGHT);
	}
	
	@Test
	public void testOverweightBelowPounds() {
		//test case(BMI < 30 when weight is lighter)
		HealthIndicator.WeightStatus overweightBelow = HealthIndicator.calculateWeightStatus(5, 4.94, 179, 21);
		assertEquals(overweightBelow,HealthIndicator.WeightStatus.OVERWEIGHT);
	}
	
	@Test
	public void testOverweightAboveHeight() {
		//test case(BMI < 30 when height is taller)
		HealthIndicator.WeightStatus overweightBelow1 = HealthIndicator.calculateWeightStatus(5, 4.95, 180, 21);
		assertEquals(overweightBelow1,HealthIndicator.WeightStatus.OVERWEIGHT);
	}
	
	@Test
	public void testObsesEqual() {
		//test case(BMI = 30)
		HealthIndicator.WeightStatus obeseabove = HealthIndicator.calculateWeightStatus(5, 4.94, 180, 21);
		assertEquals(obeseabove,HealthIndicator.WeightStatus.OBESE);
	}
	
	@Test
	public void testObsesBelowHeight() {
		//test case(BMI > 30 when height is shorter)
		HealthIndicator.WeightStatus obeseabove1 = HealthIndicator.calculateWeightStatus(5, 4.93, 180, 21);
		assertEquals(obeseabove1,HealthIndicator.WeightStatus.OBESE);
	}
	
	@Test
	public void testObsesAbovePounds() {
		//test case(BMI > 30 when weight is heavier)
		HealthIndicator.WeightStatus obeseabove2 = HealthIndicator.calculateWeightStatus(5, 4.94, 181, 21);
		assertEquals(obeseabove2,HealthIndicator.WeightStatus.OBESE);
	}
	
	@Test
	public void testIllegalArgumentExceptionFeetBelowZero() {
		//test case(Feet < 0)
		try {
			HealthIndicator.WeightStatus feetBelowZero = HealthIndicator.calculateWeightStatus(-5, 4.94, 181, 21);
			assertEquals(feetBelowZero, HealthIndicator.WeightStatus.OBESE);
			fail("Feet cannot below 0");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionFeetTooSmall() {
		//test case(Feet too small)
		try {
			HealthIndicator.WeightStatus feetTooSmall = HealthIndicator.calculateWeightStatus(1, 4.94, 181, 21);
			assertEquals(feetTooSmall, HealthIndicator.WeightStatus.OBESE);
			fail("Feet cannot be too small");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionFeetTooLarge() {
		//test case(feet too large)
		try {
			HealthIndicator.WeightStatus feetTooLarge = HealthIndicator.calculateWeightStatus(22, 5, 181, 21);
			assertEquals(feetTooLarge, HealthIndicator.WeightStatus.UNDERWEIGHT);
			fail("feet cannot be too large");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionInchBelowZero() {
		//test case(Inch < 0)
		try {
			HealthIndicator.WeightStatus inchBelowZero = HealthIndicator.calculateWeightStatus(5, -2, 181, 21);
			assertEquals(inchBelowZero, HealthIndicator.WeightStatus.OBESE);
			fail("Inch cannot below 0");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionInchOverTwelve() {
		//test case(Inch >= 12)
		try {
			HealthIndicator.WeightStatus inchOverTwelve = HealthIndicator.calculateWeightStatus(5, 13, 181, 21);
			assertEquals(inchOverTwelve, HealthIndicator.WeightStatus.OVERWEIGHT);
			fail("Inch cannot over or equal 12");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionWeightBelowZero() {
		//test case(Weight < 0)
		try {
			HealthIndicator.WeightStatus weightBelowZero = HealthIndicator.calculateWeightStatus(5, 7, -2, 21);
			assertEquals(weightBelowZero, HealthIndicator.WeightStatus.UNDERWEIGHT);
			fail("Weight cannot below 0");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionWeightTooSmall() {
		//test case(Weight too small)
		try {
			HealthIndicator.WeightStatus weightTooSmall = HealthIndicator.calculateWeightStatus(5, 7, 10, 21);
			assertEquals(weightTooSmall, HealthIndicator.WeightStatus.UNDERWEIGHT);
			fail("Weight cannot be too small");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionWeightTooLarge() {
		//test case(Weight too large)
		try {
			HealthIndicator.WeightStatus weightTooLarge = HealthIndicator.calculateWeightStatus(5, 7, 1000, 21);
			assertEquals(weightTooLarge, HealthIndicator.WeightStatus.OBESE);
			fail("Weight cannot be too large");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionAgeTooLarge() {
		//test case(Age too large)
		try {
			HealthIndicator.WeightStatus ageTooLarge = HealthIndicator.calculateWeightStatus(5, 7, 200, 210);
			assertEquals(ageTooLarge, HealthIndicator.WeightStatus.OBESE);
			fail("Age cannot be too large");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}
	
	@Test
	public void testIllegalArgumentExceptionAgeBelowZero() {
		//test case(Age < 0)
		try {
			HealthIndicator.WeightStatus ageBelowZero = HealthIndicator.calculateWeightStatus(5, 7, 200, -6);
			assertEquals(ageBelowZero, HealthIndicator.WeightStatus.OBESE);
			fail("Age cannot below 0");
			}
			catch (IllegalArgumentException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		

	}

}
