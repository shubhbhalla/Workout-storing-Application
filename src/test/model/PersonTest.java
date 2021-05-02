package model;

import model.exception.NegativeInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    private Person testPerson;
    private List<Integer> reps;
    private List<Integer> repsTwo;
    private List<Double> weights;
    private List<Double> weightTwo;
    private Exercise newEx;
    private Exercise secondEx;
    private Workout newWorkout;
    private Workout anotherWorkout;

    @BeforeEach
    public void setup() {
        testPerson = new Person("test", 17, 80, 90, 1.5, "Male");

        weights = new ArrayList<>();
        reps = new ArrayList<>();
        weights.add(90.5);
        reps.add(10);
        newEx = new Exercise("Bench", weights, 1, reps);
        newWorkout = new Workout(60, newEx);

        weightTwo = new ArrayList<>();
        repsTwo = new ArrayList<>();
        weightTwo.add(50.5);
        weightTwo.add(100.5);
        repsTwo.add(20);
        repsTwo.add(5);
        secondEx = new Exercise("Rows", weightTwo, 2, repsTwo);
        anotherWorkout = new Workout(90, newEx);
        anotherWorkout.addExercise(secondEx);
    }

    @Test
    public void testConstructor() {
        assertEquals("test", testPerson.getName());
        assertEquals(17, testPerson.getAge());
        assertEquals(80, testPerson.getHeight());
        assertEquals(90, testPerson.getWeight());
        assertEquals(1.5, testPerson.getIntensity());
        assertEquals("Male", testPerson.getGender());
    }

    @Test
    public void testChangeHeightNoException() {
        assertEquals(80, testPerson.getHeight());
        int oldTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * 80 +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(oldTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
        try {
            testPerson.changeHeight(99);
        } catch (NegativeInputException e) {
            fail("Did not expect exception");
        }
        assertEquals(99, testPerson.getHeight());
        int newTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * 99 +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(newTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeHeightException() {
        assertEquals(80, testPerson.getHeight());
        int oldTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * 80 +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(oldTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
        try {
            testPerson.changeHeight(-99);
            fail("Expected exception");
        } catch (NegativeInputException e) {
            // Expected
        }
        assertEquals(80, testPerson.getHeight());
        int newTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * 80 +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(newTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeWeightNoException() {
        assertEquals(90, testPerson.getWeight());
        int oldTdee = (int) (9.99 * 90 / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(oldTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
        try {
            testPerson.changeWeight(110);
        } catch (NegativeInputException e) {
            fail("Did not expect exception");
        }
        assertEquals(110, testPerson.getWeight());
        int newTdee = (int) (9.99 * 110 / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(newTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeWeightException() {
        assertEquals(90, testPerson.getWeight());
        int oldTdee = (int) (9.99 * 90 / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(oldTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
        try {
            testPerson.changeWeight(-110);
            fail("Expected exception");
        } catch (NegativeInputException e) {
            // expected
        }
        assertEquals(90, testPerson.getWeight());
        int newTdee = (int) (9.99 * 90 / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(newTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeGender() {
        assertEquals("Male", testPerson.getGender());
        int oldTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(oldTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
        testPerson.changeGender("Female");
        assertEquals("Female", testPerson.getGender());
        int newTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) - 161);
        assertEquals(newTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeAgeNoException() {
        assertEquals(17, testPerson.getAge());
        int oldTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * 17) + 5);
        assertEquals(oldTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
        try {
            testPerson.changeAge(30);
        } catch (NegativeInputException e) {
            fail("Did not expect exception");
        }
        assertEquals(30, testPerson.getAge());
        int newTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * 30) + 5);
        assertEquals(newTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeAgeException() {
        assertEquals(17, testPerson.getAge());
        int oldTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * 17) + 5);
        assertEquals(oldTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
        try {
            testPerson.changeAge(-30);
            fail("Expected exception");
        } catch (NegativeInputException e) {
            // expected
        }
        assertEquals(17, testPerson.getAge());
        int newTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * 17) + 5);
        assertEquals(newTdee * testPerson.getIntensity(), testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeIntensity() {
        assertEquals(1.5, testPerson.getIntensity());
        int oldTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(oldTdee * 1.5, testPerson.getTdee(), 2.0);
        testPerson.changeIntensity(1.9);
        assertEquals(1.9, testPerson.getIntensity());
        int newTdee = (int) (9.99 * testPerson.getWeight() / 2.2 + 6.25 * testPerson.getHeight() +
                (-4.92 * testPerson.getAge()) + 5);
        assertEquals(newTdee * 1.9, testPerson.getTdee(), 2.0);
    }

    @Test
    public void testChangeName() {
        assertEquals("test", testPerson.getName());
        testPerson.changeName("Shubh");
        assertEquals("Shubh", testPerson.getName());
    }

    @Test
    public void testAddWorkoutRecord() {
        assertEquals(0, testPerson.getWorkoutRecords().size());

        testPerson.addWorkoutRecord(newWorkout);
        testPerson.addWorkoutRecord(anotherWorkout);

        assertEquals(2, testPerson.getWorkoutRecords().size());
        assertEquals(newWorkout, testPerson.getWorkoutRecords().get(0));
        assertEquals(anotherWorkout, testPerson.getWorkoutRecords().get(1));
    }

    @Test
    public void testChangeWorkout() {
        assertEquals(0, testPerson.getWorkoutRecords().size());

        testPerson.addWorkoutRecord(newWorkout);
        testPerson.addWorkoutRecord(newWorkout);

        assertEquals(2, testPerson.getWorkoutRecords().size());
        assertEquals(newWorkout, testPerson.getWorkoutRecords().get(0));
        assertEquals(newWorkout, testPerson.getWorkoutRecords().get(1));

        testPerson.changeWorkout(1, anotherWorkout);

        assertEquals(2, testPerson.getWorkoutRecords().size());
        assertEquals(newWorkout, testPerson.getWorkoutRecords().get(0));
        assertEquals(anotherWorkout, testPerson.getWorkoutRecords().get(1));
    }

    @Test
    public void testCalculateTdee() {
        int tdee = testPerson.getTdee();
        int calculatedTdee = (int) ((9.99 * 90 / 2.2 + 6.25 * 80) +
                (-4.92 * 17) + 5);
        assertEquals(calculatedTdee * 1.5, tdee, 2.0);
    }
}