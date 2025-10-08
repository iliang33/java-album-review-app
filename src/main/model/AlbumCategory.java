package model;

import java.util.ArrayList;
import java.util.List;

// Represents a named category of albums that can be used to group album reviews
//   with similar themes
public class AlbumCategory {
    private String name;
    private List<Album> albumList;

    // EFFECTS: Creates an album category with the given name and creates an empty
    // list of stored albums
    public AlbumCategory(String name) {
        this.name = name;
        albumList = new ArrayList<>();
    }

    // REQUIRES: an album with the same name and artist is not already in the
    // tracklist
    // MODIFIES: this
    // EFFECTS: adds given album to album list
    public void addAlbum(Album album) {
        this.albumList.add(album);
    }

    // MODIFIES: this
    // EFFECTS: Remove album with given name and artist from album list, do nothing
    // if it's not there
    public void removeAlbum(String name, String artist) {
        int indexOfAlbumToRemove = -1;
        for (int i = 0; i < this.albumList.size(); i++) {
            Album currentAlbum = this.albumList.get(i);
            if (currentAlbum.getName().equals(name) && currentAlbum.getArtist().equals(artist)) {
                indexOfAlbumToRemove = i;
            }

        }
        if (indexOfAlbumToRemove != -1) {
            this.albumList.remove(indexOfAlbumToRemove);

        }

    }

    // REQUIRES: 0.0 <= rating <= 10.0
    // EFFECTS: Create an album with the given info and return it
    public Album createAlbum(String name, String artist, String genre, double rating,
            String review) {
        return new Album(name, artist, genre, rating, review);
    }

    public String getName() {
        return this.name;
    }

    public List<Album> getAlbumList() {
        return this.albumList;
    }

    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns name and artist of each album in album list as a string
    public String albumListToString() {
        String stringAlbumlist = "";
        for (int i = 0; i < this.albumList.size(); i++) {
            Album currentAlbum = this.albumList.get(i);

            stringAlbumlist += i + 1 + ". ";
            stringAlbumlist += currentAlbum.getName();
            stringAlbumlist += " by " + currentAlbum.getArtist();

            if (i != this.albumList.size() - 1) {
                stringAlbumlist += "\n";
            }

        }
        return stringAlbumlist;
    }
}
