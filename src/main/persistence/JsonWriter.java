package persistence;

import model.Album;
import model.AlbumCategory;
import org.json.JSONObject;

import java.io.*;

// referenced from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a writer that writes the JSON representation of Album and AlbumCategory to file

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter JsonWriter;
    private String destinationFile;

    // EFFECTS: constructs a Json writer that writes to given destination file
    public JsonWriter(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: opens the Json writer and throws a FileNotFoundException if
    // destination file cannot be found or opened
    public void open() throws FileNotFoundException {
        JsonWriter = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of AlbumCategory to file
    public void write(AlbumCategory category) {
        JSONObject json = category.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Album to file
    public void write(Album album) {
        JSONObject json = album.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        JsonWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        JsonWriter.print(json);
    }
}
