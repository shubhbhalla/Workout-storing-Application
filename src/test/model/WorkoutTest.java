package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    private Workout workout;
    private List<Double> weights;
    private List<Integer> reps;
    private Exercise newEx;
    private List<Double> weightTwo;
    private List<Integer> repsTwo;
    private Exercise secondEx;
    private Exercise thirdEx;
    private int duration;

    @BeforeEach
    public void setup() {
        weights = new ArrayList<>();
        weightTwo = new ArrayList<>();
        reps = new ArrayList<>();
        repsTwo = new ArrayList<>();
        weights.add(90.5);
        reps.add(10);
        weightTwo.add(50.5);
        weightTwo.add(100.5);
        repsTwo.add(20);
        repsTwo.add(5);
        duration = 60;

        newEx = new Exercise("Bench", weights, 1, reps);
        secondEx = new Exercise("Rows", weightTwo, 2, repsTwo);
        thirdEx = new Exercise("Rows", weights, 1, reps);
        workout = new Workout(duration, newEx);
    }


    @Test
    public void testSetWorkoutDuration() {
        int output = workout.getWorkoutDuration();
        assertEquals(this.duration, output);
        workout.setWorkoutDuration(90);
        int newOutput = workout.getWorkoutDuration();
        assertEquals(90, newOutput);
    }

    @Test
    public void testGetExercises() {
        workout.addExercise(secondEx);
        workout.addExercise(thirdEx);

        assertEquals(3, workout.getAllExercise().size());
        assertEquals(2, workout.getExercises("Rows").size());
        assertEquals(secondEx, workout.getExercises("Rows").get(0));
        assertEquals(thirdEx, workout.getExercises("Rows").get(1));
    }

    @Test
    public void testAddExercise() {
        assertEquals(1, workout.getAllExercise().size());
        assertEquals(newEx, workout.getExerciseAtIndex(0));
        workout.addExercise(secondEx);
        workout.addExercise(thirdEx);
        assertEquals(3, workout.getAllExercise().size());
        assertEquals(newEx, workout.getExerciseAtIndex(0));
        assertEquals(secondEx, workout.getExerciseAtIndex(1));
        assertEquals(thirdEx, workout.getExerciseAtIndex(2));
    }

    @Test
    public void testRemoveSpecificExerciseFromZeroIndex() {
        workout.addExercise(secondEx);
        assertEquals(2, workout.getAllExercise().size());
        assertEquals(newEx, workout.getExerciseAtIndex(0));
        assertEquals(secondEx, workout.getExerciseAtIndex(1));
        workout.removeSpecificExercise(0);
        assertEquals(1, workout.getAllExercise().size());
        assertEquals(secondEx, workout.getExerciseAtIndex(0));
    }

    @Test
    public void testRemoveSpecificExerciseFromOneIndex() {
        workout.addExercise(secondEx);
        workout.addExercise(thirdEx);
        assertEquals(3, workout.getAllExercise().size());
        assertEquals(newEx, workout.getExerciseAtIndex(0));
        assertEquals(secondEx, workout.getExerciseAtIndex(1));
        assertEquals(thirdEx, workout.getExerciseAtIndex(2));
        workout.removeSpecificExercise(1);
        assertEquals(2, workout.getAllExercise().size());
        assertEquals(newEx, workout.getExerciseAtIndex(0));
        assertEquals(thirdEx, workout.getExerciseAtIndex(1));
    }
}
