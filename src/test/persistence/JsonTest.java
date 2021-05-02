package persistence;

import model.Exercise;
import model.Person;
import model.Workout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    protected void assertPerson(Person p) {
        assertEquals("test", p.getName());
        assertEquals(19, p.getAge());
        assertEquals(190, p.getHeight());
        assertEquals(190, p.getWeight());
        assertEquals(1.35, p.getIntensity());
        assertEquals("Female", p.getGender());
        List<Workout> wk = p.getWorkoutRecords();
        assertEquals(1, wk.size());
        List<Exercise> ex = wk.get(0).getAllExercise();
        assertEquals(1, ex.size());
        List<Integer> i = new ArrayList<>();
        i.add(1);
        assertEquals(i, ex.get(0).getReps());
        assertEquals(1, ex.get(0).getSets());
        assertEquals("Rowing", ex.get(0).getName());
        List<Double> w = new ArrayList<>();
        w.add(1.0);
        assertEquals(w, ex.get(0).getWeight());
    }

    protected void assertPersonTwo(Person p) {
        assertEquals("test2", p.getName());
        assertEquals(25, p.getAge());
        assertEquals(170, p.getHeight());
        assertEquals(200, p.getWeight());
        assertEquals(1.5, p.getIntensity());
        assertEquals("Male", p.getGender());
        assertTrue(p.getWorkoutRecords().isEmpty());
    }
}
