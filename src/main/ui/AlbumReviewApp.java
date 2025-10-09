package ui;

import model.Album;
import model.AlbumCategory;
import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Album Review Application
public class AlbumReviewApp {

    // make sure you that when you call a method with user input
    // that before you pass it to the method, it fits the requires clause

    // when taking user input for ratings, make sure it is between 0.0 and 10.0
    // inclusive,
    // if not, let user know and tell them to enter a valid value by using while
    // loop with scanner
    // consider helper methods for this

    // ensure there are messages tha are printed to let the user know if an action
    // is
    // performed succesfully or not

    private List<AlbumCategory> categories;
    private List<Album> albums;
    private Scanner scan;

    // EFFECTS: runs the application and initializes both the scanner and the lists
    // used to track album categories and albums,
    public AlbumReviewApp() {
        this.categories = new ArrayList<>();
        this.albums = new ArrayList<>();
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

    // WHEN REACH 25 LINE LIMIT, MAKE HELPER FUNCTIONS TO SPLIT IT,
    // LIKE processCreationInput, processListingInput, processSortingInput etc....

    public void processInput(String input) {
        if (input.equalsIgnoreCase("ca")) {
            createAlbum();
        } else if (input.equalsIgnoreCase("atl")) {
            addToTrackList();
        } else if (input.equalsIgnoreCase("ra")) {
            removeAlbum();
        } else if (input.equalsIgnoreCase("cc")) {
            createCategory();
        } else if (input.equalsIgnoreCase("rc")) {
            removeCategory();
        } else if (input.equalsIgnoreCase("aac")) {
            addToCategory();
        } else if (input.equalsIgnoreCase("rac")) {
            removeFromCategory();
        } else if (input.equalsIgnoreCase("l")) {
            printAllReviews();
        } else if (input.equalsIgnoreCase("lc")) {
            printAllCategories();
        } else if (input.equalsIgnoreCase("saa")) {
            sortAlbumsByAlphabeticalArtist();
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
        System.out.println("\tType ra to remove an album review\n");

        System.out.println("\tType cc to create a category");
        System.out.println("\tType rc to remove a category");
        System.out.println("\tType aac to add an album to a category");
        System.out.println("\tType rac to remove an album from a category\n");

        System.out.println("\tType l to list all reviews");
        System.out.println("\tType lc to list all categories\n");

        System.out.println("\tType saa to sort all album reviews by artist alphabetical");
        System.out.println("\tType sna to sort all album reviews by name alphabetical");
        System.out.println("\tType sra to sort all album reviews by rating high to low\n");

        System.out.println("\tType ua to update an album review");

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
    // album list if there isn't already and album with the same name and artist
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

        int indexOfAlbumToAddSongTo = getIndexOfWantedAlbum(name, artist);
        boolean addMoreSongs = true;

        if (indexOfAlbumToAddSongTo != -1) {
            while (addMoreSongs) {
                if (!promptUserToAddSongs(indexOfAlbumToAddSongTo)) {
                    addMoreSongs = false;
                }

            }
        } else {
            System.out.println("Album not found");
        }

    }

    // EFFECTS: asks users for information to add songs to a tracklist
    // and returns true if user wants to add more songs, false otherwise

    // this is a helper function for addToTrackList()
    public boolean promptUserToAddSongs(int indexOfAlbumToAddSongTo) {
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
            return false;
        } else {
            return true;
        }

    }

    // MODIFIES: this
    // EFFECTS: removes an album with the given name and artist from the album list
    public void removeAlbum() {
        System.out.println("Enter name of album to remove");
        String name = scan.nextLine();

        System.out.println("Enter album artist name to remove");
        String artist = scan.nextLine();

        int indexOfAlbumToRemove = getIndexOfWantedAlbum(name, artist);

        if (indexOfAlbumToRemove != -1) {
            this.albums.remove(indexOfAlbumToRemove);

        } else {
            System.out.println("Album not found");
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
    // EFFECTS: removes the category with the given name from category list
    public void removeCategory() {
        System.out.println("Enter name of category to remove");
        String name = scan.nextLine();

        int indexOfCategoryToRemove = getIndexOfWantedCategory(name);

        if (indexOfCategoryToRemove != -1) {
            this.categories.remove(indexOfCategoryToRemove);

        } else {
            System.out.println("Category not found");
        }

    }

    // MODIFIES: this
    // EFFECTS: add the given album to the category with the given name
    public void addToCategory() {
        System.out.println("Enter name of category to add to");
        String name = scan.nextLine();

        System.out.println("Enter name of album to add");
        String albumName = scan.nextLine();

        System.out.println("Enter name of artist of album to add");
        String artistName = scan.nextLine();

        int indexOfCategoryToAddto = getIndexOfWantedCategory(name);
        int indexOfAlbum = getIndexOfWantedAlbum(albumName, artistName);

        if (indexOfCategoryToAddto != -1) {
            if (indexOfAlbum != -1) {
                this.categories.get(indexOfCategoryToAddto).addAlbum(this.albums.get(indexOfAlbum));
                this.albums.remove(indexOfAlbum);
            } else {
                System.out.println("Album not found. Is it created?");
            }
        } else {
            System.out.println("Category not found. Is it created?");
        }

    }

    // MODIFIES: this
    // EFFECTS: remove the given album from the category with the given name
    public void removeFromCategory() {
        System.out.println("Enter name of category to remove from");
        String name = scan.nextLine();

        System.out.println("Enter name of album to remove");
        String albumName = scan.nextLine();

        System.out.println("Enter name of artist of album to remove");
        String artistName = scan.nextLine();

        int indexOfCategoryToRemoveFrom = getIndexOfWantedCategory(name);
        int indexOfAlbum = getIndexOfWantedAlbum(albumName, artistName);

        if (indexOfCategoryToRemoveFrom != -1) {
            if (indexOfAlbum != 1) {
                this.categories.get(indexOfCategoryToRemoveFrom).removeAlbum(albumName, artistName);
            } else {
                System.out.println("Album not found. Is it created?");
            }
        } else {
            System.out.println("Category not found. Is it created?");
        }
    }

    // EFFECTS: prints all reviews
    // user is informed if there are no reviews
    public void printAllReviews() {
        System.out.println("\n");

        if (!this.albums.isEmpty()) {
            System.out.println("Albums:\n");
            for (Album album : albums) {
                System.out.println(album.toString());
                System.out.println("Tracklist:\n" + album.trackListToString());

            }
        } else {
            System.out.println("You have no reviews!");
        }

    }

    // EFFECTS: prints out name of all categories and the info about the albums
    // in them
    public void printAllCategories() {
        System.out.println("\n");

        if (!this.categories.isEmpty()) {
            System.out.println("Categories:\n");

            for (AlbumCategory category : categories) {
                System.out.println(category.getName() + ":\n");
                for (Album album : category.getAlbumList()) {
                    System.out.println(album.toString());
                    System.out.println("Tracklist:\n" + album.trackListToString());
                    System.out.println("\n");
                }
            }

        } else {
            System.out.println("You have no categories!");
        }

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by artist alphabetically then prints out the sorted
    // album list
    public void sortAlbumsByAlphabeticalArtist() {
        for (int i = 0; i < this.albums.size(); i++) {
            for (int j = 0; j < this.albums.size(); j++) {
                // a negative result from compareTo means the string on the left should go
                // before
                // the string on the right
                if (this.albums.get(i).getArtist().compareToIgnoreCase(this.albums.get(j).getArtist()) < 0) {
                    // stores the album so it doesn't get lost during swapping
                    Album currentAlbumComparingToOthers = this.albums.get(i);

                    // swap positions
                    this.albums.set(i, this.albums.get(j));
                    this.albums.set(j, currentAlbumComparingToOthers);

                }
            }

        }
        printAllReviews();

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
    // EFFECTS: updates the name of a category given by oldName to newName
    public void updateCategoryName(String oldName, String newName) {

    }

    // MODIFIES: this
    // EFFECTS: updates a given String album field (name, artist, genre, review) to
    // a new given value
    public void updateAlbumNotRatingField(String albumName, String fieldName, String newValue) {
        // update the one in the all albums list and in the corresponding category if it
        // is in one

    }

    // MODIFIES: this
    // EFFECTS: updates given album rating to the given rating
    public void updateAlbumRating(String albumName, double newRating) {
        // update the one in the all albums list and in the corresponding category if it
        // is in one

    }

    // EFFECTS: return the index of the album in albums list specified by name and
    // artist
    public int getIndexOfWantedAlbum(String name, String artist) {
        int indexOfWantedAlbum = -1;
        for (int i = 0; i < this.albums.size(); i++) {
            Album currentAlbum = this.albums.get(i);
            if (currentAlbum.getName().equalsIgnoreCase(name) && currentAlbum.getArtist().equalsIgnoreCase(artist)) {
                indexOfWantedAlbum = i;
            }

        }
        return indexOfWantedAlbum;

    }

    // EFFECTS: return the index of the category in albums list specified by name
    public int getIndexOfWantedCategory(String name) {

        int indexOfWantedCategory = -1;
        for (int i = 0; i < this.categories.size(); i++) {
            AlbumCategory currentCategory = this.categories.get(i);
            if (currentCategory.getName().equalsIgnoreCase(name)) {
                indexOfWantedCategory = i;
            }

        }
        return indexOfWantedCategory;

    }

}
