package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a song with a name, artist, rating, and a review
public class Song implements Writable {
    private String name;
    private String artist;
    private double rating;
    private String review;

    // REQUIRES: 0.0 <= rating <= 10.0
    // EFFECTS: Creates a song with the given name, artist, rating, and
    // review
    public Song(String name, String artist, double rating, String review) {
        this.name = name;
        this.artist = artist;
        this.rating = rating;
        this.review = review;

    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public double getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }

    @Override
    // EFFECTS: returns information about the song as a string
    public String toString() {
        return "Name: " + this.name + "\nArtist: " + this.artist + "\nRating: "
                + this.rating + "\nReview: " + this.review;
    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns this song as a JSON object

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("artist", this.artist);
        json.put("rating", this.rating);
        json.put("review", this.review);
        return json;
    }
}
