package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents an album with a name, artist, genre, tracklist of songs, rating, and review
public class Album implements Writable {
    private String name;
    private String artist;
    private String genre;
    private List<Song> tracklist;
    private double rating;
    private String review;

    // REQUIRES: 0.0 <= rating <= 10.0
    // EFFECTS: creates an album with a name, artist, genre, an empty tracklist,
    // rating, and review
    public Album(String name, String artist, String genre, double rating, String review) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.tracklist = new ArrayList<>();
        this.rating = rating;
        this.review = review;

    }

    // REQUIRES: a song with the same name and artist is not already in the
    // tracklist
    // MODIFIES: this
    // EFFECTS: adds given song to tracklist
    public void addSong(Song song) {
        this.tracklist.add(song);
    }

    // MODIFIES: this
    // EFFECTS: Remove song with given name and artist from tracklist, do nothing if
    // not there
    public void removeSong(String name, String artist) {
        // value of -1 means the song was not found
        int indexOfSongToRemove = -1;
        for (int i = 0; i < this.tracklist.size(); i++) {
            Song currentSong = this.tracklist.get(i);
            if (currentSong.getName().equalsIgnoreCase(name) && currentSong.getArtist().equalsIgnoreCase(artist)) {
                indexOfSongToRemove = i;
            }

        }
        if (indexOfSongToRemove != -1) {
            this.tracklist.remove(indexOfSongToRemove);

        }

    }

    // MODIFIES: this, albumToMergeWith
    // EFFECTS: combines albumToMergeWith tracklist with this album's tracklist.
    // duplicates are allowed
    public void mergeAlbum(Album albumToMergeWith) {
        // clone used to work around concurrent modification exception
        List<Song> tracklistAlbumToMergeWithClone = new ArrayList<>();

        for (Song song : albumToMergeWith.getTracklist()) {
            tracklistAlbumToMergeWithClone.add(song);
        }
        for (Song song : tracklistAlbumToMergeWithClone) {
            this.tracklist.add(song);
            albumToMergeWith.getTracklist().remove(song);

        }

    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getGenre() {
        return this.genre;
    }

    public List<Song> getTracklist() {
        return this.tracklist;
    }

    public double getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    // EFFECTS: returns all information about the album as a string except for
    // tracklist
    @Override
    public String toString() {
        return "Name: " + this.name + "\nArtist: " + this.artist + "\nGenre: " + this.genre + "\nRating: "
                + this.rating + "\nReview: " + this.review;
    }

    // EFFECTS: returns the name of each song and artist in tracklist as a string
    public String trackListToString() {
        String stringTracklist = "";
        for (int i = 0; i < this.tracklist.size(); i++) {
            Song currentSong = this.tracklist.get(i);

            stringTracklist += "\t"; // indents each song
            stringTracklist += i + 1 + ". "; // numbers song in tracklist
            stringTracklist += currentSong.getName();
            stringTracklist += " by " + currentSong.getArtist();

            // makes each song on a seperate line
            if (i != this.tracklist.size() - 1) {
                stringTracklist += "\n";
            }

        }
        return stringTracklist;
    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns this album as a JSON object

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("artist", this.artist);
        json.put("genre", this.genre);
        json.put("rating", this.rating);
        json.put("review", this.review);
        json.put("tracklist", tracklistToJson());
        return json;
    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns songs in this album's tracklist as a JSON array
    private JSONArray tracklistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song song : this.tracklist) {
            jsonArray.put(song.toJson());
        }

        return jsonArray;
    }

}
