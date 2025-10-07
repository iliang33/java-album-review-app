package model;

// Represents a song with a name, artist, length (mins and seconds parts), rating, and a review
public class Song {
    private String name;
    private String artist;
    private int lengthMinsPart;
    private int lengthSecsPart;
    private double rating;
    private String review;

    // REQUIRES: lengthMinsPart >= 0, 0 <= lengthSecPart <= 59, 0.0 <= rating <=
    // 10.0
    // EFFECTS: Creates a song with the given name, artist, length (mins and second
    // parts), rating, and
    // review
    public Song(String name, String artist, int lengthMinsPart, int lengthSecsPart, double rating, String review) {
        this.name = name;
        this.artist = artist;
        this.lengthMinsPart = lengthMinsPart;
        this.lengthSecsPart = lengthSecsPart;

        this.rating = rating;
        this.review = review;

    }

    // REQUIRES: amount > 0.0, this.getRating() + amount <= 10.0
    // MODIFIES: this
    // EFFECTS: increases song rating by the given amount
    public void increaseRating(double amount) {
        this.rating += amount;

    }

    // REQUIRES: amount > 0.0, this.getRating() - amount >= 0.0
    // MODIFIES: this
    // EFFECTS: decreases song rating by the given amount
    public void decreaseRating(double amount) {
        this.rating -= amount;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setLength(int lengthMinsPart, int lengthSecsPart) {
        this.lengthMinsPart = lengthMinsPart;
        this.lengthSecsPart = lengthSecsPart;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public int getLengthMinsPart() {
        return this.lengthMinsPart;
    }

    public int getLengthSecsPart() {
        return this.lengthSecsPart;
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
        return "Name: " + this.name + "\nArtist: " + this.artist + "\nLength: " + this.lengthMinsPart + " mins "
                + this.lengthSecsPart + " secs" + "\nRating: "
                + this.rating + "\nReview: " + this.review;
    }
}
