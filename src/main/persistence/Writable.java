package persistence;

import org.json.JSONObject;

/* Used code from repository -
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
*/

// an interface so that each class implements the method toJson()
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
