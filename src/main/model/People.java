package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// Represents all the Person objects (It is basically a list of Person)
public class People implements Writable {
    private List<Person> people;

    public People() {
        this.people = new ArrayList<>();
    }

    // setter
    // REQUIRES: a person should have been present at index i
    // MODIFIES: this
    // EFFECTS: changes the person at the index i with Person p
    public void changePerson(int i, Person p) {
        this.people.set(i, p);
    }

    // getters
    // REQUIRES: a person should exist at the index i
    // EFFECTS: gets the person at the index i of this
    public Person getPerson(int index) {
        return this.people.get(index);
    }

    // EFFECTS: returns all the people in this
    public List<Person> getEveryone() {
        return this.people;
    }

    // REQUIRES: p != null
    // MODIFIES: this
    // EFFECTS: adds a Person p at the end of the list
    public void addPerson(Person p) {
        this.people.add(p);
    }

    // EFFECTS: returns the size of People
    public int size() {
        return this.people.size();
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject job = new JSONObject();

        for (Person p : people) {
            job.put(p.getName(), p.toJson());
        }

        return job;
    }
}
