package model;

import java.util.ArrayList;
import java.util.List;

// Represents an album with a name, artist, genre, number of songs, length (mins and seconds parts), tracklist of songs
//  overall rating, and review
public class Album {
    private String name;
    private String artist;
    private String genre;
    private List<Song> tracklist;
    private int numberOfSongs;
    private int lengthMinsPart;
    private int lengthSecsPart;
    private double rating;
    private String review;

    // REQUIRES: 0.0 <= rating <= 10.0
    // EFFECTS: Creates an album with a name, artist, genre, an empty tracklist
    // (meaning number of songs and length (mins and seconds parts) are also zero),
    // rating, and review
    public Album(String name, String artist, String genre, double rating, String review) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.tracklist = new ArrayList<>();
        this.numberOfSongs = 0;
        this.lengthMinsPart = 0;
        this.lengthSecsPart = 0;
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
        int indexOfSongToRemove = -1;
        for (int i = 0; i < this.tracklist.size(); i++) {
            Song currentSong = this.tracklist.get(i);
            if (currentSong.getName().equals(name) && currentSong.getArtist().equals(artist)) {
                indexOfSongToRemove = i;
            }

        }
        if (indexOfSongToRemove != -1) {
            this.tracklist.remove(indexOfSongToRemove);

        }

    }

    // REQUIRES: lengthMinsPart >= 0, 0 <= lengthSecPart <= 59, 0.0 <= rating <=
    // 10.0
    // MODIFIES: this
    // EFFECTS: Create a song with the given info and return it
    public Song createSong(String name, String artist, int lengthMinsPart, int lengthSecsPart, double rating,
            String review) {
        return new Song(name, artist, lengthMinsPart, lengthSecsPart, rating, review);

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

    public int getNumberOfSongs() {
        return this.numberOfSongs;
    }

    public int getLengthMinsPart() {
        return this.lengthMinsPart;
    }

    public int getLengthSecsPart() {
        return this.lengthSecsPart;
    }

    public List<Song> getTracklist() {
        return this.tracklist;
    }

    public double getRating() {
        return this.rating;
    }

    // EFFECTS: Gets the average rating for all songs in the album rounded to 1
    // decimal place
    public double getAverageRating() {
        // when implementing use javas decimal formatting
        double total = 0.0;

        for (Song song : tracklist) {
            total += song.getRating();
        }

        // returns the average rounded to one decimal place
        // total / this.tracklist.size() is the average
        // multiplying it by 10.0 shifts the decimal one place and Math.round
        // rounds to the nearest integer. Finally, dividing by 10.0, moves the
        // decimal back one place
        return Math.round((total / this.tracklist.size()) * 10.0) / 10.0;
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

    public void setNumberOfSongs(int num) {
        this.numberOfSongs = num;
    }

    // MODIFIES: this
    // EFFECTS: sets the length to the total length of the songs in the tracklist
    public void setLength() {

        int totalLengthMinPart = 0;
        int totalLengthSecPart = 0;

        for (Song song : this.tracklist) {
            totalLengthMinPart += song.getLengthMinsPart();
            totalLengthSecPart += song.getLengthSecsPart();
        }

        int convertedMinsFromSecs = Math.floorDiv(totalLengthSecPart, 60);
        int leftoverSecs = 0;

        if (totalLengthSecPart < 60) {
            leftoverSecs = totalLengthSecPart;
        } else {
            leftoverSecs = totalLengthSecPart % 60;
        }

        this.lengthMinsPart = totalLengthMinPart + convertedMinsFromSecs;
        this.lengthSecsPart = leftoverSecs;
    }

    public void setTracklist(List<Song> tracklist) {
        this.tracklist = tracklist;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    // EFFECTS: returns formatted information about the album as a string except for
    // tracklist
    public String toString() {
        return "Name: " + this.name + "\nArtist: " + this.artist + "\nGenre: " + this.genre + "\nNumber of songs: "
                + this.getNumberOfSongs() + "\nLength: "
                + this.lengthMinsPart + " mins "
                + this.lengthSecsPart + " secs" + "\nRating: "
                + this.rating + "\nReview: " + this.review;
    }

    // EFFECTS: returns formatted tracklist as a string
    public String trackListToString() {
        String stringTracklist = "";
        for (int i = 0; i < this.tracklist.size(); i++){
            stringTracklist += i + 1 + ". ";
            stringTracklist += this.tracklist.get(i).getName();

            if (i != this.tracklist.size() - 1){
                stringTracklist += "\n";
            }

        }
        return stringTracklist;

    }

}
