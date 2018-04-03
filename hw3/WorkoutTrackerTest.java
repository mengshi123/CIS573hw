package edu.upenn.cis573;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class WorkoutTrackerTest {
	
	private WorkoutTracker workoutTracker;
	private Date d1,d2,d3,d4,d5;
	private Workout w1,w2,w3,w4,w5;
	
	@Before
	public void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	    d1 = sdf.parse("2017-10-01 00:00:00");
        d2 = sdf.parse("2017-10-02 00:00:00");
        d3 = sdf.parse("2017-10-03 00:00:00");
        d4 = sdf.parse("2017-10-04 00:00:00");
        d5 = sdf.parse("2017-10-05 00:00:00");
		w1 = new Workout(d1, 20);
		w2 = new Workout(d2, 25);
		w3 = new Workout(d3, 30);
		w4 = new Workout(d4, 35);
		w5 = new Workout(d5, 30);
		workoutTracker = new WorkoutTracker();		
	}

	@Test
	public void testExpectedWorkoutBelowThree(){
		//test case(the numbers of work out in the list < 3)
    	assertEquals(workoutTracker.addWorkout(w1),40);
		assertEquals(workoutTracker.addWorkout(w2),45);
		assertEquals(workoutTracker.addWorkout(w3),45);
	}
	
	@Test
	public void testExpectedWorkoutEqualThree(){
		//test case(the numbers of work out in the list = 3)
		workoutTracker.workouts.add(w1);
		workoutTracker.workouts.add(w2);
		workoutTracker.workouts.add(w3);
		assertEquals(workoutTracker.addWorkout(w4),40);
	}	
	
	@Test
	public void testExpectedList(){
		//test case(add work out to the end and not changing the original list)
		workoutTracker.workouts.add(w1);
		workoutTracker.workouts.add(w2);
		workoutTracker.workouts.add(w3);
		workoutTracker.workouts.add(w4);
		for(int i = 0; i < workoutTracker.workouts.size(); i++){
			if(i == 0) assertEquals(workoutTracker.workouts.get(i).getDurationMinutes(),20);
			if(i == 1) assertEquals(workoutTracker.workouts.get(i).getDurationMinutes(),25);
			if(i == 2) assertEquals(workoutTracker.workouts.get(i).getDurationMinutes(),30);
			if(i == 3) assertEquals(workoutTracker.workouts.get(i).getDurationMinutes(),35);
		}
		assertEquals(workoutTracker.addWorkout(w5), 30);
	}
	
	@Test
	public void testIllegalArgumentExceptionWorkoutDurationBelow0(){

		Workout badWork = new Workout(d5, -5);
		try {
			workoutTracker.addWorkout(badWork);
			// duration cannot below 0
			fail("workout cannot below 0");
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
	public void testIllegalStateExceptionWorkoutDurationBelow0(){

		Workout badWork = new Workout(d1, -5);
		workoutTracker.workouts.add(badWork);
		try {
			workoutTracker.addWorkout(w2);
			// duration cannot below 0
			fail("workout cannot below 0");
			}
			catch (IllegalStateException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		
	}
	
	@Test
	public void testIllegalArgumentExceptionWorkoutDurationTooLong(){

		Workout badWork = new Workout(d5, 1000);
		try {
			workoutTracker.addWorkout(badWork);
			// duration cannot be too long
			fail("workout cannot be too long");
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
	public void testIllegalArgumentExceptionWorkoutDurationTooLong_1(){

		workoutTracker.workouts.add(w1);
		Workout badWork = new Workout(d2, 120);
		workoutTracker.workouts.add(badWork);
		try {
			workoutTracker.addWorkout(w3);
			// the sum duration in the workout list is not over 150 but after adding a new workout it will be.
			fail("over 150 after adding");
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
	public void testIllegalStateExceptionWorkoutTooLarge(){

		Workout badWork = new Workout(d1, 1000);
		workoutTracker.workouts.add(badWork);
		try {
			workoutTracker.addWorkout(w2);
			// The sum duration of workout in the list is over 150, it's in illegal state.
			fail("The sum duration of workout in the list is over 150, it's in illegal state.");
			}
			catch (IllegalStateException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		
	}
	
	@Test
	public void testIllegalArgumentExceptionDateBefore() throws ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Date beforeDate = sdf.parse("2017-09-05 00:00:00");
		Workout badWork = new Workout(beforeDate, 10);
		try {
			workoutTracker.addWorkout(w1);
			workoutTracker.addWorkout(badWork);
			// cannot add a workout whose date is earlier than other workout
			fail("cannot add a workout whose date is earlier than other workout");
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
	public void testIllegalStateExceptionDateBefore() throws ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Date beforeDate = sdf.parse("2017-09-05 00:00:00");
		Workout badWork = new Workout(beforeDate, 10);
		workoutTracker.workouts.add(w1);
		workoutTracker.workouts.add(badWork);
		try {
			workoutTracker.addWorkout(w2);
			// If the date in the workout list is not in order, it's in illegal state.
			fail("the date in the workout list is not in order, it's in illegal state.");
			}
			catch (IllegalStateException e) {
			// made it here, that¡¯s good
			// now check that there are no side effects
			}
			catch (Throwable t) {
			// addWorkout threw wrong thing, that¡¯s bad
			fail("Cannot add this workout");
			}		
	}
}
	


