package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeopleTest {
    private People people;
    private Person testPerson;
    private Person testPersonTwo;
    private Person testPersonThree;

    @BeforeEach
    public void setup() {
        people = new People();
        testPerson = new Person("test", 17, 80, 80, 1.5, "Male");
        testPersonTwo = new Person("test2", 20, 70, 100, 1.725, "Female");
        testPersonThree = new Person("test3", 30, 10, 50, 1.9, "Male");
        people.addPerson(testPerson);
    }

    @Test
    public void testAddPersonOnce() {
        assertEquals(1, people.size());
        checkTestPersonIsAtIndexZero();
    }

    @Test
    public void testAddPersonTwice() {
        people.addPerson(testPersonTwo);

        assertEquals(2, people.size());

        checkTestPersonIsAtIndexZero();

        checkTestPersonTwoIsAtIndexOne();
    }

    @Test
    public void testChangePersonAtIndexZero() {
        people.addPerson(testPersonTwo);
        assertEquals(2, people.size());
        people.changePerson(0, testPersonThree);
        assertEquals(2, people.size());

        checkTestPersonTwoIsAtIndexOne();

        assertEquals("test3", people.getPerson(0).getName());
        assertEquals(30, people.getPerson(0).getAge());
        assertEquals(10, people.getPerson(0).getHeight());
        assertEquals(50, people.getPerson(0).getWeight());
        assertEquals(1.9, people.getPerson(0).getIntensity());
        assertEquals("Male", people.getPerson(0).getGender());
    }

    @Test
    public void testChangePersonAtIndexTwo() {
        people.addPerson(testPersonTwo);
        people.addPerson(testPersonThree);

        assertEquals("test3", people.getPerson(2).getName());
        assertEquals(3, people.size());

        people.changePerson(2, testPerson);

        assertEquals(3, people.size());

        checkTestPersonIsAtIndexZero();
        checkTestPersonTwoIsAtIndexOne();

        assertEquals("test", people.getPerson(2).getName());
        assertEquals(17, people.getPerson(2).getAge());
        assertEquals(80, people.getPerson(2).getHeight());
        assertEquals(80, people.getPerson(2).getWeight());
        assertEquals(1.5, people.getPerson(2).getIntensity());
        assertEquals("Male", people.getPerson(2).getGender());
    }

    @Test
    public void testGetEveryone() {
        people.addPerson(testPersonTwo);
        people.addPerson(testPersonThree);

        assertEquals(3, people.size());
        List<Person> output = people.getEveryone();
        assertEquals(3, output.size());

        assertEquals("test", output.get(0).getName());
        assertEquals(17, output.get(0).getAge());
        assertEquals(80, output.get(0).getHeight());
        assertEquals(80, output.get(0).getWeight());
        assertEquals(1.5, output.get(0).getIntensity());
        assertEquals("Male", output.get(0).getGender());

        assertEquals("test2", output.get(1).getName());
        assertEquals(20, output.get(1).getAge());
        assertEquals(70, output.get(1).getHeight());
        assertEquals(100, output.get(1).getWeight());
        assertEquals(1.725, output.get(1).getIntensity());
        assertEquals("Female", output.get(1).getGender());

        assertEquals("test3", output.get(2).getName());
        assertEquals(30, output.get(2).getAge());
        assertEquals(10, output.get(2).getHeight());
        assertEquals(50, output.get(2).getWeight());
        assertEquals(1.9, output.get(2).getIntensity());
        assertEquals("Male", output.get(2).getGender());
    }

    // helper methods
    // EFFECTS: checks if the person at index 0 of People object is the Person object testPerson
    private void checkTestPersonIsAtIndexZero() {
        assertEquals("test", people.getPerson(0).getName());
        assertEquals(17, people.getPerson(0).getAge());
        assertEquals(80, people.getPerson(0).getHeight());
        assertEquals(80, people.getPerson(0).getWeight());
        assertEquals(1.5, people.getPerson(0).getIntensity());
        assertEquals("Male", people.getPerson(0).getGender());
    }

    // EFFECTS: checks if the person at index 1 of People object is the Person object testPersonTwo
    private void checkTestPersonTwoIsAtIndexOne() {
        assertEquals("test2", people.getPerson(1).getName());
        assertEquals(20, people.getPerson(1).getAge());
        assertEquals(70, people.getPerson(1).getHeight());
        assertEquals(100, people.getPerson(1).getWeight());
        assertEquals(1.725, people.getPerson(1).getIntensity());
        assertEquals("Female", people.getPerson(1).getGender());
    }
}
