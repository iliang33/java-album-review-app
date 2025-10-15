package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Album;
import model.AlbumCategory;
import model.Song;

// referenced from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads album category and album from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads album category from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AlbumCategory readAlbumCategory() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAlbumCategory(jsonObject);
    }

    // EFFECTS: reads album from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Album readAlbum() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAlbum(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses album category from JSON object and returns it
    private AlbumCategory parseAlbumCategory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        AlbumCategory category = new AlbumCategory(name);
        addAlbums(category, jsonObject);
        return category;
    }

    // EFFECTS: parses album from JSON object and returns it
    private Album parseAlbum(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        String genre = jsonObject.getString("genre");
        double rating = jsonObject.getDouble("rating");
        String review = jsonObject.getString("review");

        Album album = new Album(name, artist, genre, rating, review);
        addTracklist(album, jsonObject);
        return album;
    }

    // MODIFIES: category
    // EFFECTS: parses albums from JSON object and adds them to album category
    private void addAlbums(AlbumCategory category, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("albums");
        for (Object json : jsonArray) {
            JSONObject nextAlbum = (JSONObject) json;
            addAlbum(category, nextAlbum);
        }
    }

    // MODIFIES: category
    // EFFECTS: parses album from JSON object and adds it to album category
    private void addAlbum(AlbumCategory category, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        String genre = jsonObject.getString("genre");
        double rating = jsonObject.getDouble("rating");
        String review = jsonObject.getString("review");

        Album album = new Album(name, artist, genre, rating, review);

        category.addAlbum(album);
    }

    // MODIFIES: album
    // EFFECTS: parses tracklist from JSON object and adds them to album
    private void addTracklist(Album album, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tracklist");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(album, nextSong);
        }
    }

    // MODIFIES: album
    // EFFECTS: parses song from JSON object and adds it to album
    private void addSong(Album album, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        double rating = jsonObject.getDouble("rating");
        String review = jsonObject.getString("review");

        Song song = new Song(name, artist, rating, review);
        album.addSong(song);
    }
}
