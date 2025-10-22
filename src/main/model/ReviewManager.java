package model;

import java.util.List;

// Represents a manager that stores and handles operation on all created album review and album categories
public class ReviewManager {

    // EFFECTS: creates a review manager with no stored album reviews or album
    // categories
    public ReviewManager() {

    }

    // REQUIRES: album is not already in list
    // MODIFIES: this
    // EFFECTS: adds given album to all albums list
    public void addAlbum(Album album) {

    }

    // REQUIRES: the album exists
    // MODIFIES: this
    // EFFECTS: removes given album (referenced by name and artist) from all albums
    // list
    public void removeAlbum(String name, String artist) {

    }

    // REQUIRES: album exists and song is not already in list
    // EFFECTS: creates and adds song with the given info to the tracklist of the
    // album with the given info
    public void addToAlbumTracklist(String albumName, String albumArtist, String songName, String songArtist,
            double songRating, String songReview) {

    }

    // REQUIRES: songNumber >= 1 && songNumber <= tracklist size and the album exists
    // EFFECTS: removes the song in the given position number in tracklist from
    // given album (referenced by name and artist)
    public void removeFromAlbumTracklist(String albumName, String albumArtist, int songNumber) {
    }

    // REQUIRES: category is not already in list
    // MODIFIES: this
    // EFFECTS: adds given category to all categories list
    public void addCategory(AlbumCategory category) {

    }

    // REQUIRES: the category exists
    // MODIFIES: this
    // EFFECTS: removes given category (referenced by name) from all categories
    // list
    public void removeCategory(String name) {

    }

    // REQUIRES: category exists and album is not already in category
    // EFFECTS: adds album with the given info to category with the given name
    public void addToCategory(String categoryName, String albumName, String albumArtist) {
    }

    // REQUIRES: the album and the category both exist
    // MODIFIES: this
    // EFFECTS: removes the album with given info from the category with given name
    public void removeFromCategory(String categoryName, String albumName, String albumArtist) {

    }

    // EFFECTS: prints all reviews
    public void printAllReviews() {
    }

    // EFFECTS: prints out name of all categories and the info about the albums
    // in them
    public void printAllCategories() {
    }

    // MODIFIES: this
    // EFFECTS: sorts album list by artist alphabetically then prints out the sorted
    // album list. If two albums have the same artist, the album added first is
    // shown first
    public void sortAlbumsByAlphabeticalArtist() {
    }

    // MODIFIES: this
    // EFFECTS: sorts album list by name alphabetically then prints out the sorted
    // album list. if two albums have the same name, the one added first is shown
    // first
    public void sortAlbumsByAlphabeticalName() {
    }

    // MODIFIES: this
    // EFFECTS: sorts album list by rating high to low. if two albums have same
    // rating, the album added first is shown first
    public void sortAlbumsByRating() {
    }

    // EFFECTS: return album specified by name and
    // artist. returns null if not found
    public Album getWantedAlbum(String name, String artist) {
        return null;
    }

    // EFFECTS: return category specified by name, returns null if not found
    public AlbumCategory getWantedCategory(String name) {
        return null;
    }

    // EFFECTS: return album specified by name
    // artist in given category. returns null if not found
    public Album getWantedAlbumInWantedCategory(String name, String artist, AlbumCategory category) {
        return null;
    }

    // EFFECTS: return song specified by name that is in the given album's
    // tracklist. returns null if not found
    public Song getWantedSongInTracklist(String name, Album album) {
        return null;
    }

    // EFFECTS: returns true if the given album
    // found in any category, false otherwise
    public boolean albumIsInAnyCategory(Album album) {
        return false;
    }

    public List<Album> getAlbumsList() {
        return null;

    }

    public List<AlbumCategory> getAlbumCategoriesList() {
        return null;

    }

}
