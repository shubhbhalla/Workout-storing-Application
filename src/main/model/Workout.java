package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// Represents a new Workout (It is basically a list of exercises)
public class Workout implements Writable {
    private List<Exercise> exercises;
    private int timeToCompleteWorkout;

    // REQUIRES: duration > 0
    // MODIFIES: this
    // EFFECTS: add the exercises to the workout and sets the timeToCompleteWorkout equal to duration
    public Workout(int duration, Exercise... exercises) {
        this.exercises = new ArrayList<>();
        this.exercises.addAll(Arrays.asList(exercises));
        this.timeToCompleteWorkout = duration;
    }

    // getters
    // REQUIRES: an exercise should exist at index i
    // EFFECTS: returns the exercise at the i index from this
    public Exercise getExerciseAtIndex(int i) {
        return this.exercises.get(i);
    }

    public int getWorkoutDuration() {
        return this.timeToCompleteWorkout;
    }

    // setters
    // REQUIRES: i is in minutes
    // MODIFIES: this
    // EFFECTS: sets the duration of the workout to i
    public void setWorkoutDuration(int i) {
        this.timeToCompleteWorkout = i;
    }

    // EFFECTS: returns the all exercises as a list
    public List<Exercise> getAllExercise() {
        return this.exercises;
    }

    // EFFECTS: returns the all exercises having name n in the same order as they are found
    public List<Exercise> getExercises(String n) {
        List<Exercise> allExercises = new ArrayList<>();
        for (Exercise ex : this.exercises) {
            if (ex.getName().equals(n)) {
                allExercises.add(ex);
            }
        }
        return allExercises;
    }

    // REQUIRES: ex != null
    // MODIFIES: this
    // EFFECTS: Add the exercise at the end of the given list of exercises
    public void addExercise(Exercise ex) {
        this.exercises.add(ex);
    }

    // REQUIRES: the exercise should exist at index i of exercises
    // MODIFIES: this
    // EFFECTS: Removes the exercise at the i index from the list of exercises and moves
    // all the other exercises down one index to fill the gap
    public void removeSpecificExercise(int i) {
        this.exercises.remove(i);
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("duration", getWorkoutDuration());
        json.put("exercises", thingiesToJson());
        return json;
    }

    // EFFECTS: returns exercises in this workout as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise ex : exercises) {
            jsonArray.put(ex.toJson());
        }

        return jsonArray;
    }
}
