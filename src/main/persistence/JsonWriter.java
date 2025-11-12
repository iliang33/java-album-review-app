package persistence;

import model.ReviewManager;

import org.json.JSONObject;

import java.io.*;

// referenced from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a writer that writes the JSON representation of ReviewManager to file
public class JsonWriter {
    private static final int TAB = 4;

    private PrintWriter jsonWriter;
    private String destinationFile;

    // EFFECTS: constructs a Json writer that writes to given destination file
    public JsonWriter(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: opens the Json writer and throws a FileNotFoundException if
    // destination file cannot be found or opened
    public void open() throws FileNotFoundException {
        jsonWriter = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ReviewManager to file
    public void writeReviewManager(ReviewManager manager) {
        JSONObject json = manager.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        jsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        jsonWriter.print(json);
    }
}
