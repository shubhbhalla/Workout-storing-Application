package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// Creates an exercise with the specified name, reps, sets and weights (pounds)
public class Exercise implements Writable {
    private String nameOfExercise;
    private List<Double> weights;
    private int sets;
    private List<Integer> reps;


    // INVARIANT: The corresponding index in the weight list and reps list are associated to each other,
    // hence the len(weight) is always equal to len(reps) is always equal to sets
    // REQUIRES: weight (in pounds), len(name) > 0
    // MODIFIES: this
    // EFFECTS: sets the name, weights used, reps performed and # of sets of the exercise
    public Exercise(String name, List<Double> weight, int sets, List<Integer> reps) {
        this.nameOfExercise = name;
        this.weights = weight;
        this.sets = sets;
        this.reps = reps;
    }

    // setters
    public void changeName(String name) {
        this.nameOfExercise = name;
    }

    // getters
    public String getName() {
        return this.nameOfExercise;
    }

    public int getSets() {
        return this.sets;
    }

    // REQUIRES: len(weight) is always equal to len(reps) is always equal to sets
    // MODIFIES: this
    // EFFECTS: sets this.sets to sets
    public void setSets(int sets) {
        this.sets = sets;
    }

    public List<Double> getWeight() {
        return this.weights;
    }

    // REQUIRES: len(weight) is always equal to len(reps) is always equal to sets
    // MODIFIES: this
    // EFFECTS: sets this.weights to weight
    public void setWeight(List<Double> weight) {
        this.weights = weight;
    }

    public List<Integer> getReps() {
        return this.reps;
    }

    // REQUIRES: len(weight) is always equal to len(reps) is always equal to sets
    // MODIFIES: this
    // EFFECTS: sets this.reps to reps
    public void setReps(List<Integer> reps) {
        this.reps = reps;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", nameOfExercise);
        json.put("weights", weights);
        json.put("reps", reps);
        json.put("sets", sets);
        return json;
    }
}
