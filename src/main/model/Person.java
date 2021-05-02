package model;

import model.exception.NegativeInputException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// Represents a person (user) who has workout records, having weight (pounds), name,
// age, height (cm), gender and intensity of their exercise
public class Person implements Writable {
    private String nameOfPerson;
    private List<Workout> workoutRecord;
    private int tdee;
    private int age;
    private int height;
    private int weight;
    private double intensity;
    private String gender;

    // MODIFIES: this
    // EFFECTS: calculates the tdee, sets the name, age, height, weight, intensity and gender of the person
    public Person(String name, int age, int height, int weight, double intensity,
                  String gender) {
        this.nameOfPerson = name;
        this.workoutRecord = new ArrayList<>();
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.intensity = intensity;
        this.gender = gender;
        calculateTdee();
    }

    // getters
    public String getName() {
        return this.nameOfPerson;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public double getIntensity() {
        return this.intensity;
    }

    public String getGender() {
        return this.gender;
    }

    public int getTdee() {
        return this.tdee;
    }

    public List<Workout> getWorkoutRecords() {
        return this.workoutRecord;
    }

    // setters
    // MODIFIES: this
    // EFFECTS: changes the height and calculates the new TDEE
    public void changeHeight(int height) throws NegativeInputException {
        if (height < 0) {
            throw new NegativeInputException("Negative height entered");
        }
        this.height = height;
        calculateTdee();
    }

    // MODIFIES: this
    // EFFECTS: changes the weight and calculates the new TDEE
    public void changeWeight(int weight) throws NegativeInputException {
        if (weight < 0) {
            throw new NegativeInputException("Negative weight entered");
        }
        this.weight = weight;
        calculateTdee();
    }

    // MODIFIES: this
    // EFFECTS: changes the gender to g, and calculates the new TDEE
    public void changeGender(String g) {
        this.gender = g;
        calculateTdee();
    }

    // MODIFIES: this
    // EFFECTS: changes the age and calculates the new TDEE
    public void changeAge(int age) throws NegativeInputException {
        if (age < 0) {
            throw new NegativeInputException("Negative height entered");
        }
        this.age = age;
        calculateTdee();
    }

    // MODIFIES: this
    // EFFECTS: changes the intensity to i and calculates the new TDEE
    public void changeIntensity(double i) {
        this.intensity = i;
        calculateTdee();
    }

    // MODIFIES: this
    // EFFECTS: changes the name of the person to the name n
    public void changeName(String n) {
        this.nameOfPerson = n;
    }

    // MODIFIES: this
    // EFFECTS: changes the workout for the specified index i
    public void changeWorkout(int i, Workout workout) {
        this.workoutRecord.set(i, workout);
    }

    // MODIFIES: this
    // EFFECTS: adds the workout at the end of the person's workout record
    public void addWorkoutRecord(Workout workout) {
        this.workoutRecord.add(workout);
    }

    // MODIFIES: this
    // EFFECTS: calculates the total daily expenditure and changes this.tdee to the value calculated
    public void calculateTdee() {
        int i = 5;
        if (getGender().equals("Female")) {
            i = -161;
        }
        double calculatedTdee = (9.99 * getWeight() / 2.2)
                + (6.25 * getHeight()) + (-4.92 * getAge()) + i;
        calculatedTdee *= getIntensity();
        this.tdee = (int) calculatedTdee;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("height", height);
        json.put("weight", weight);
        json.put("age", age);
        json.put("intensity", intensity);
        json.put("gender", gender);
        json.put("workouts", thingiesToJson());
        return json;
    }

    // EFFECTS: returns workouts in this as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout w : workoutRecord) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}
