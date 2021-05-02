package persistence;

import model.Exercise;
import model.People;
import model.Person;
import model.Workout;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// Represents a reader that reads people from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads people from file and returns it;
    // throws IOException if an error occurs reading data from file
    public People read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePeople(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses people from JSON object and returns it
    private People parsePeople(JSONObject jsonObject) {
        People people = new People();
        addPeople(people, jsonObject);
        return people;
    }

    // MODIFIES: people
    // EFFECTS: parses person from JSON object and adds them to people
    private void addPeople(People people, JSONObject jsonObject) {
        Set<String> keySet = jsonObject.keySet();
        for (String name : keySet) {
            JSONObject attributes = jsonObject.getJSONObject(name);
            double intensity = attributes.getDouble("intensity");
            String gender = attributes.getString("gender");
            int weight = attributes.getInt("weight");
            int age = attributes.getInt("age");
            int height = attributes.getInt("height");
            Person person = new Person(name, age, height, weight, intensity, gender);
            people.addPerson(person);
            addWorkouts(attributes.getJSONArray("workouts"), person);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses the workout records from JSON Array and adds them to Person p
    private void addWorkouts(JSONArray workouts, Person p) {
        for (int i = 0; i < workouts.length(); i++) {
            JSONObject workout = workouts.getJSONObject(i);
            int duration = workout.getInt("duration");
            Workout wk = new Workout(duration);
            JSONArray ex = workout.getJSONArray("exercises");
            addExercisesToWorkout(wk, ex);
            p.addWorkoutRecord(wk);
        }
    }

    // MODIFIES: wk
    // EFFECTS: parses exercises from JSON Array and adds them to Workout wk
    private void addExercisesToWorkout(Workout wk, JSONArray ex) {
        for (int j = 0; j < ex.length(); j++) {
            JSONObject exercise = ex.getJSONObject(j);
            int sets = exercise.getInt("sets");
            String name = exercise.getString("name");
            JSONArray repsAsJsonArray = exercise.getJSONArray("reps");
            List<Integer> reps = new ArrayList<>();
            for (Object obj : repsAsJsonArray.toList()) {
                reps.add(Integer.parseInt(String.valueOf(obj)));
            }
            JSONArray weightsAsJsonArray = exercise.getJSONArray("weights");
            List<Double> weights = new ArrayList<>();
            for (Object obj : weightsAsJsonArray.toList()) {
                weights.add(Double.parseDouble(String.valueOf(obj)));
            }
            Exercise exObj = new Exercise(name, weights, sets, reps);
            wk.addExercise(exObj);
        }
    }
}
