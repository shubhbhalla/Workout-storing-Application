package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    private Exercise exercise;
    private List<Double> weights;
    private List<Integer> reps;
    private Integer sets;

    @BeforeEach
    public void setup() {
        weights = new ArrayList<>();
        reps = new ArrayList<>();
        weights.add(80.5);
        weights.add(100.5);
        weights.add(97.5);
        weights.add(90.0);
        reps.add(4);
        reps.add(5);
        reps.add(10);
        reps.add(8);
        sets = 4;

        exercise = new Exercise("Bench_Press", weights, sets, reps);
    }

    @Test
    public void testConstructor() {
        assertEquals("Bench_Press", exercise.getName());

        assertEquals(4, exercise.getWeight().size());
        assertEquals(80.5, exercise.getWeight().get(0));
        assertEquals(100.5, exercise.getWeight().get(1));
        assertEquals(97.5, exercise.getWeight().get(2));
        assertEquals(90.0, exercise.getWeight().get(3));

        assertEquals(4, exercise.getReps().size());
        assertEquals(4, exercise.getReps().get(0));
        assertEquals(5, exercise.getReps().get(1));
        assertEquals(10, exercise.getReps().get(2));
        assertEquals(8, exercise.getReps().get(3));

        assertEquals(4, exercise.getSets());
    }

    @Test
    public void testChangeName() {
        assertEquals("Bench_Press", exercise.getName());
        exercise.changeName("Incline_Dumbbell_Press");
        assertEquals("Incline_Dumbbell_Press", exercise.getName());
    }

    @Test
    public void testSetWeightAndRepsAndSets() {
        weights = new ArrayList<>();
        weights.add(3.5);
        weights.add(4.5);
        weights.add(5.0);
        reps = new ArrayList<>();
        reps.add(10);
        reps.add(11);
        reps.add(12);
        sets = 3;

        exercise.setWeight(weights);
        exercise.setReps(reps);
        exercise.setSets(sets);

        assertEquals(3, exercise.getWeight().size());
        assertEquals(3.5, exercise.getWeight().get(0));
        assertEquals(4.5, exercise.getWeight().get(1));
        assertEquals(5.0, exercise.getWeight().get(2));

        assertEquals(3, exercise.getReps().size());
        assertEquals(10, exercise.getReps().get(0));
        assertEquals(11, exercise.getReps().get(1));
        assertEquals(12, exercise.getReps().get(2));

        assertEquals(3, exercise.getSets());
    }
}
