package ui;

import model.Album;
import model.AlbumCategory;
import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Album Review Application
public class AlbumReviewApp {

    // remember that it is up to the callers responsiblity to fufill the requires
    // clause
    // not the method specification

    // so make sure you ensure that when you call a method with user input
    // that before you pass it to the method, it fits the requires clause
    // so use ifs to check.

    private List<AlbumCategory> categories;
    private List<Album> albums;
    private List<Song> singles;
    private Scanner scan;

    // EFFECTS: runs the application and initializes both the scanner and the lists
    // used
    // to track album categories, albums, and singles
    public AlbumReviewApp() {
        this.categories = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.singles = new ArrayList<>();
        this.scan = new Scanner(System.in);

        runApp();
    }

    // MODIFIES: this
    // EFFECTS: takes user input
    public void runApp() {
        boolean hasExit = false;
        String input = null;

        while (!hasExit) {
            showMainOptions();

            input = scan.nextLine();
            input.toLowerCase();

            if (input.equalsIgnoreCase("e")) {
                hasExit = true;
                System.out.println("\nApp Exited");
            } else {
                processInput(input);
            }

        }

    }

    // MODIFIES: this
    // EFFECTS: acts upon user input
    public void processInput(String input) {
        if (input.equalsIgnoreCase("ca")) {
            createAlbum();
        } else if (input.equalsIgnoreCase("atl")) {
            addToTrackList();
        } else if (input.equalsIgnoreCase("ra")) {
            removeAlbum();
        } else if (input.equalsIgnoreCase("cs")) {
            createSingle();
        } else if (input.equalsIgnoreCase("rs")) {
            removeSingle();
        } else if (input.equalsIgnoreCase("cc")) {
            createCategory();

        } else if (input.equalsIgnoreCase("l")) {
            printAllReviews();
        } else {
            System.out.println("Not a valid input");
        }

    }

    // EFFECTS: prints out options
    public void showMainOptions() {
        System.out.println("\nOptions:");

        System.out.println("\tType e to exit the app\n");

        System.out.println("\tType ca to create an album review");
        System.out.println("\tType atl to add songs to the tracklist of an existing album review");
        System.out.println("\tType ra to remove an album review");
        System.out.println("\tType cs to create a single review");
        System.out.println("\tType rs to remove an single review\n");

        System.out.println("\tType cc to create a category");
        System.out.println("\tType ac to add to a category");
        System.out.println("\tType rc to remove from a category\n");

        System.out.println("\tType l to list all reviews\n");

        System.out.println("\tType saaa to sort an album by artist alphabetical");
        System.out.println("\tType sana to sort an album by name alphabetical");
        System.out.println("\tType sara to sort an album by rating high to low\n");

        System.out.println("\tType ssaa to sort a single by artist alphabetical");
        System.out.println("\tType ssna to sort a single by name alphabetical");
        System.out.println("\tType ssra to sort a single by rating high to low\n");

        System.out.println("\tType ua to update an album review");
        System.out.println("\tType us to update an single review");

        System.out.println("\tType at to add songs to an album tracklist");

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
    public void createAlbum() {
        System.out.println("Enter name of album");
        String name = scan.nextLine();

        System.out.println("Enter name of artist");
        String artist = scan.nextLine();

        System.out.println("Enter genre");
        String genre = scan.nextLine();

        System.out.println("Enter album rating");
        double rating = Double.parseDouble(scan.nextLine());

        System.out.println("Enter review");
        String review = scan.nextLine();

        this.albums.add(new Album(name, artist, genre, rating, review));

    }

    // EFFECTS: takes user input to add songs to the tracklist of an album
    public void addToTrackList() {
        System.out.println("Enter name of album to add tracklist to");
        String name = scan.nextLine();

        System.out.println("Enter name of artist");
        String artist = scan.nextLine();

        int indexOfAlbumToAddSongTo = -1;
        boolean addMoreSongs = true;

        for (int i = 0; i < this.albums.size(); i++) {
            if (albums.get(i).getName().equalsIgnoreCase(name) && albums.get(i).getArtist().equalsIgnoreCase(artist)) {
                indexOfAlbumToAddSongTo = i;
            }
        }

        if (indexOfAlbumToAddSongTo != -1) {
            while (addMoreSongs) {
                System.out.println("Enter name of song");
                String songName = scan.nextLine();

                System.out.println("Enter name of artist");
                String artistName = scan.nextLine();

                System.out.println("Enter song rating");
                double rating = Double.parseDouble(scan.nextLine());

                System.out.println("Enter review");
                String review = scan.nextLine();

                this.albums.get(indexOfAlbumToAddSongTo).addSong(new Song(songName, artistName, rating, review));

                System.out.println("add more songs? (type n to stop, anything otherwise)");
                String response = scan.nextLine();

                if (response.equalsIgnoreCase("n")) {
                    addMoreSongs = false;

                }

            }
        } else {
            System.out.println("Album not found");
        }

    }

    // MODIFIES: this
    // EFFECTS: removes an album with the given name and artist from the album list
    public void removeAlbum() {
        System.out.println("Enter name of album to remove");
        String name = scan.nextLine();

        System.out.println("Enter album artist name to remove");
        String artist = scan.nextLine();

        int indexOfAlbumToRemove = -1;
        for (int i = 0; i < this.albums.size(); i++) {
            Album currentAlbum = this.albums.get(i);
            if (currentAlbum.getName().equalsIgnoreCase(name) && currentAlbum.getArtist().equalsIgnoreCase(artist)) {
                indexOfAlbumToRemove = i;
            }

        }
        if (indexOfAlbumToRemove != -1) {
            this.albums.remove(indexOfAlbumToRemove);

        }

    }

    // MODIFIES: this
    // EFFECTS: creates a new single with the given information and adds it to the
    // single list
    public void createSingle() {
        System.out.println("Enter name of single");
        String name = scan.nextLine();

        System.out.println("Enter name of artist");
        String artist = scan.nextLine();

        System.out.println("Enter single rating");
        double rating = Double.parseDouble(scan.nextLine());

        System.out.println("Enter review");
        String review = scan.nextLine();

        this.singles.add(new Song(name, artist, rating, review));

    }

    // MODIFIES: this
    // EFFECTS: removes a single with the given name and artist from the single list
    public void removeSingle() {
        System.out.println("Enter name of single to remove");
        String name = scan.nextLine();

        System.out.println("Enter single artist name to remove");
        String artist = scan.nextLine();

        int indexOfSingleToRemove = -1;
        for (int i = 0; i < this.singles.size(); i++) {
            Song currentSingle = this.singles.get(i);
            if (currentSingle.getName().equalsIgnoreCase(name) && currentSingle.getArtist().equalsIgnoreCase(artist)) {
                indexOfSingleToRemove = i;
            }

        }
        if (indexOfSingleToRemove != -1) {
            this.singles.remove(indexOfSingleToRemove);

        }

    }

    // MODIFIES: this
    // EFFECTS: creates a new category with the given name and adds it to the
    // categories list
    public void createCategory() {
        System.out.println("Enter desired category name");
        String name = scan.nextLine();

        this.categories.add(new AlbumCategory(name));

    }

    // MODIFIES: this
    // EFFECTS: add the given album to the category with the given name and
    // remove the album from the album list
    public void addToCategory(String name, Album album) {

    }

    // MODIFIES: this
    // EFFECTS: remove the given album from the category with the given name
    public void removeFromCategory(String name, Album album) {

    }

    // EFFECTS: prints all reviews (albums and singles)
    // user is informed if there are no reviews
    public void printAllReviews() {
        System.out.println("\n");

        if (!this.albums.isEmpty() || !!this.categories.isEmpty() || this.singles.isEmpty()) {
            System.out.println("Albums:\n");
            for (Album album : albums) {
                System.out.println(album.toString());
                System.out.println("Tracklist:\n" + album.trackListToString());
                System.out.println("\n");

            }
            for (AlbumCategory category : categories) {
                for (Album album : category.getAlbumList()) {
                    System.out.println(album.toString());
                    System.out.println("Tracklist:\n" + album.trackListToString());
                    System.out.println("\n");
                }
            }
            System.out.println("\nSingles:\n");
            for (Song single : singles) {
                System.out.println(single.toString());
                System.out.println("\n");
            }

        } else {
            System.out.println("You have no reviews!");
        }

    }

    // EFFECTS: prints the tracklist of the album with the given name
    public void printAlbumTracklist(String name) {

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by artist alphabetically
    public void sortAlbumsByAlphabeticalArtist() {

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by name alphabetically
    public void sortAlbumsByAlphabeticalName() {

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by rating high to low
    public void sortAlbumsByRating() {

    }

    // MODIFIES: this
    // EFFECTS: sorts singles list by artist alphabetically
    public void sortSinglesByAlphabeticalArtist() {

    }

    // MODIFIES: this
    // EFFECTS: sorts singles list by name alphabetically
    public void sortSinglesByAlphabeticalName() {

    }

    // MODIFIES: this
    // EFFECTS: sorts singles list by rating high to low
    public void sortSinglesByRating() {

    }

    // MODIFIES: this
    // EFFECTS: updates a name of a category given by oldName to newName
    public void updateCategoryName(String oldName, String newName) {

    }

    // MODIFIES: this
    // EFFECTS: updates a given String album field (name, artist, genre, review) to
    // a new given value
    public void updateAlbumNotRatingField(String albumName, String fieldName, String newValue) {

    }

    // MODIFIES: this
    // EFFECTS: updates given album rating to the given rating
    public void updateAlbumRating(String albumName, double newRating) {

    }

    // MODIFIES: this
    // EFFECTS: updates a given String single field (name, artist, review) to a new
    // given value
    public void updateSingleNotRatingField(String singleName, String fieldName, String newValue) {

    }

    // MODIFIES: this
    // EFFECTS: updates given single rating to the given rating
    public void updateSingleRating(String singleName, double newRating) {

    }

}
