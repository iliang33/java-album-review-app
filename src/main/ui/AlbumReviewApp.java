package ui;

import model.Album;
import model.Song;

// Album Review Application
public class AlbumReviewApp {

    // remember that it is up to the callers responsiblity to fufill the requires
    // clause
    // not the method specification

    // so make sure you ensure that when you call a method with user input
    // that before you pass it to the method, it fits the requires clause
    // so use ifs to check.

    // when a user wants to add a album to a category or add a single to a category
    // or add a song to an album, give them the option to choose an exisiting
    // album/single/song or to create a new one then add

    // EFFECTS: runs the application and initializes both the scanner and the lists
    // used
    // to track album categories, albums, and singles
    public AlbumReviewApp() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: takes user input
    public void runApp() {

    }

    // MODIFIES: this
    // EFFECTS: acts upon user input
    public void processInput(String input) {

    }

    // EFFECTS: prints out options
    public void showMainOptions() {

    }

    // EFFECTS: prints out a sub menu of options for when viewing albums
    public void showAlbumOptions() {

    }

    // EFFECTS: prints out a sub menu of options for sorting
    public void showSortingOptions() {

    }

    // EFFECTS: prints out a sub menu of options for updating reviews/categories
    public void showEditingOptions() {

    }

    // MODIFIES: this
    // EFFECTS: creates a new album with the given information and adds it to the
    // album list
    public void createAlbum(String name, String artist, String genre, double rating, String review) {

    }

    // MODIFIES: this
    // EFFECTS: removes an album with the given name and artist from the album list
    public void removeAlbum(String name, String artist) {

    }

    // MODIFIES: this
    // EFFECTS: creates a new single with the given information and adds it to the
    // single list
    public void createSingle(String name, String artist, int lengthMinsPart, int lengthSecsPart, double rating,
            String review) {

    }

    // MODIFIES: this
    // EFFECTS: removes a single with the given name and artist from the single list
    public void removeSingle(String name, String artist) {

    }

    // MODIFIES: this
    // EFFECTS: creates a new category with the given information and adds it to the
    // categories list
    public void createCategory(String name){

    }

    // MODIFIES: this
    // EFFECTS: add the given album to the category with the given name
    public void addToCategory(String name, Album album){

    }

    // MODIFIES: this
    // EFFECTS: remove the given album from the category with the given name
    public void removeToCategory(String name, Album album){

    }

    // EFFECTS: prints all albums both in or not in categories
    //          if in a category it shows which one
    public void printAllAlbums(){

    }

    // EFFECTS: prints all albums in the category with the given name
    public void printAlbumsInCategory(String name){

    }

    // EFFECTS: prints the tracklist of the album with the given name
    public void printAlbumTracklist(String name){

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by artist alphabetically
    public void sortAlbumsByAlphabeticalArtist(){

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by name alphabetically
    public void sortAlbumsByAlphabeticalName(){

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by rating high to low
    public void sortAlbumsByRating(){

    }

    // MODIFIES: this
    // EFFECTS: sorts singles list by artist alphabetically
    public void sortSinglesByAlphabeticalArtist(){

    }
    // MODIFIES: this
    // EFFECTS: sorts singles list by name alphabetically
    public void sortSinglesByAlphabeticalName(){

    }
    // MODIFIES: this
    // EFFECTS: sorts singles list by rating high to low
    public void sortSinglesByRating(){

    }

}
