package model;

import java.util.List;

// Represents a named category of albums that can be used to group album reviews
//   with similar themes
public class AlbumCategory {

    // EFFECTS: Creates an album category with the given name and creates an empty
    // list of stored albums
    public AlbumCategory(String name) {
        // stub
    }

    // REQUIRES: an album with the same name and artist is not already in the
    // tracklist
    // MODIFIES: this
    // EFFECTS: adds given album to album list
    public void addAlbum(Album album) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Remove album with given name and artist from album list, do nothing
    // if it's not there
    public void removeAlbum(String name, String artist) {
        // stub

    }

    // REQUIRES: 0.0 <= rating <= 10.0
    // EFFECTS: Create an album with the given info and return it
    public Album createAlbum(String name, String artist, String genre, double rating,
            String review) {
        return null;
    }

    public String getName(){
        return ""; // stub
    }

    public List<Album> getAlbumList(){
        return null; // stub
    }

    public void setName(){
        // stub
    }

    // EFFECTS: returns name and artist of each album in album list as a string
    public String albumListToString(){
        return ""; // stub
    }
}
