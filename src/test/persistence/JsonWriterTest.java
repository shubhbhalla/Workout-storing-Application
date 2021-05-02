package persistence;

import model.Exercise;
import model.People;
import model.Person;
import model.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/
public class JsonWriterTest extends JsonTest {
    private People p;
    private Person personOne;
    private Workout wk;

    @BeforeEach
    public void setup() {
        p = new People();
        personOne = new Person("test", 19, 190, 190, 1.35, "Female");
        wk = new Workout(50);
        List<Double> weights = new ArrayList<>();
        weights.add(1.0);
        List<Integer> reps = new ArrayList<>();
        reps.add(1);
        Exercise ex = new Exercise("Rowing", weights, 1, reps);
        wk.addExercise(ex);
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            p.addPerson(personOne);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyPeople() {
        try {
            JsonWriter writer = new JsonWriter("./data/testEmptyPerson.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyPerson.json");
            p = reader.read();
            assertEquals(0, p.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralWorkroom() {
        try {
            p.addPerson(personOne);
            personOne.addWorkoutRecord(wk);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            p = reader.read();
            assertEquals(1, p.size());
            Person person = p.getPerson(0);
            assertPerson(person);
            List<Workout> wk = person.getWorkoutRecords();
            assertEquals(50, wk.get(0).getWorkoutDuration());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
