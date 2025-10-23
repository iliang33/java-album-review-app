package model;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
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

    @ExcludeFromJacocoGeneratedReport
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        return result;
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (artist == null) {
            if (other.artist != null)
                return false;
        } else if (!artist.equals(other.artist))
            return false;
        return true;
    }

    
    
}
