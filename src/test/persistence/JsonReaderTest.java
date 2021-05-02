package persistence;

import model.People;
import model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/person.json");
        try {
            People p = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyFile() {
        JsonReader reader = new JsonReader("./data/testEmptyPerson.json");
        try {
            People p = reader.read();
            assertEquals(0, p.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPerson.json");
        try {
            People p = reader.read();
            assertEquals(2, p.size());
            Person firstPerson = p.getPerson(1);
            assertPerson(firstPerson);
            Person secondPerson = p.getPerson(0);
            assertPersonTwo(secondPerson);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
