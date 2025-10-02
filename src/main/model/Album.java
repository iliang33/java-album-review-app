package model;

import java.util.List;

// Represents an album with a name, artist, genre, number of songs, length (mins and seconds parts), tracklist of songs
//  overall rating, and review
public class Album {

    // REQUIRES: 0.0 <= rating <= 10.0
    // EFFECTS: Creates an album with a name, artist, genre, an empty tracklist
    // (meaning number of songs and length (mins and seconds parts) are also zero),
    // rating, and a
    // review
    public Album(String name, String artist, String genre, double rating, String review) {
        // stub

    }

    // REQUIRES: lengthMinsPart >= 0, 0 <= lengthSecPart <= 59, 0.0 <= rating <=
    // 10.0
    // MODIFIES: this
    // EFFECTS: Add song to tracklist with the given info
    public void addSong(String name, String artist, int lengthMinsPart, int lengthSecsPart, double rating,
            String review) {
        // stub
    }

    // REQUIRES: song with the given name and artist is in the tracklist
    // MODIFIES: this
    // EFFECTS: Remove song with given name and artist from tracklist
    public void removeSong(String name, String artist) {
        // stub
    }

    public String getName() {
        return "";
    }

    public String getArtist() {
        return "";
    }

    public String getGenre() {
        return "";
    }

    public int getNumberOfSongs() {
        return 0;
    }

    public int getLengthMinsPart() {
        return 0;
    }

    public int getLengthSecsPart() {
        return 0;
    }

    public List<Song> getTracklist() {
        return null;
    }

    public double getRating() {
        return 0.0;
    }

    // EFFECTS: Gets the average rating for all songs in the album rounded to 1
    // decimal place
    public double getAverageRating() {
        return 0.0;
    }

    public String getReview() {
        return "";
    }

    public void setName(String name) {
        // stub
    }

    public void setArtist(String artist) {
        // stub
    }

    public void setGenre(String genre) {
        // stub
    }

    public void setNumberOfSongs(int num) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets the length to the total length of the songs in the tracklist
    public void setLength() {
        // stub
    }

    public void setTracklist(List<Song> tracklist) {
        // stub
    }

    public void setRating(double rating) {
        // stub
    }

    public void setReview(String review) {
        // stub
    }

    // EFFECTS: returns formatted information about the album as a string
    public String toString() {
        return "";
    }

}
