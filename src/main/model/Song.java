package model;


// Represents a song with a name, artist, length, rating, and a review
public class Song {

    // REQUIRES: length in the format mins:seconds, 0.0 <= rating <= 10.0
    // EFFECTS: Creates a song with the given name, artist, length, rating, and review
    public Song(String name, String artist, String length, double rating, String review) {
        // stub

    }


    // REQUIRES: amount > 0.0, this.getRating() + amount <= 10.0
    // MODIFIES: this
    // EFFECTS: increases song rating by the given amount
    public void increaseRating(double amount){
        //stub

    }

    // REQUIRES: amount > 0.0, this.getRating() - amount >= 0.0
    // MODIFIES: this
    // EFFECTS: decreases song rating by the given amount
    public void decreaseRating(double amount){
        //stub

    }

    public void setName(){
        // stub
    }

    public void setArtist(){
        // stub
    }

    public void setLength(){
        // stub
    }

    public void setReview(){
        // stub
    }

    public String getName(){
        return ""; //stub
    }

    public String getArtist(){
        return ""; //stub
    }

    public String getLength(){
        return ""; //stub
    }

    public double getRating(){
        return 0.0; //stub
    }

    public String getReview(){
        return ""; //stub
    }

    @Override
    // EFFECTS: returns formatted information about the song as a string
    public String toString(){
        return "";
    }
}
