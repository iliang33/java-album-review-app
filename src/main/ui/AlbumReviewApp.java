package ui;

import model.Album;
import model.AlbumCategory;
import model.ReviewManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import exceptions.NotInRatingRangeException;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Album Review Application
@ExcludeFromJacocoGeneratedReport
public class AlbumReviewApp {

    // the review manager stores all album review and categories and performs
    // operations on them such as adding and removing
    private ReviewManager manager;
    private Scanner scan;
    private static final String JSON_SAVE_FILE = "./data/ReviewManager.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes review manager, scanner, json writer, json reader, and
    // runs the app
    public AlbumReviewApp() throws FileNotFoundException {
        manager = new ReviewManager();
        this.scan = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_SAVE_FILE);
        jsonReader = new JsonReader(JSON_SAVE_FILE);

        runApp();
    }

    // referenced from TellerApp
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    // MODIFIES: this
    // EFFECTS: takes user input
    public void runApp() {
        boolean hasExit = false;
        String input = null;

        while (!hasExit) {
            showMainOptions();

            input = scan.nextLine();

            if (input.equalsIgnoreCase("e")) {
                hasExit = true;
                System.out.println("\nApp Exited");
            } else {
                processMainInput(input);
            }

        }
    }

    // referenced from TellerApp
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    // MODIFIES: this
    // EFFECTS: takes update sub-menu user input
    public void runUpdateSubMenu() {
        boolean goBack = false;
        String input = null;

        while (!goBack) {
            showUpdatingReviewsOptions();

            input = scan.nextLine();

            if (input.equalsIgnoreCase("b")) {
                goBack = true;
                System.out.println("\nBack to main menu");
            } else {
                processUpdateSubMenuInput(input);
            }

        }

    }

    // referenced from TellerApp
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    // MODIFIES: this
    // EFFECTS: acts upon main menu user input
    public void processMainInput(String input) {
        processAlbumRelatedCreationAndRemoval(input);
        processAlbumCategoryRelatedCreationAndRemoval(input);
        processListing(input);
        processSorting(input);
        processUpdating(input);
        processLoadingAndSaving(input);

    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving album related creation and removal
    public void processAlbumRelatedCreationAndRemoval(String input) {
        if (input.equalsIgnoreCase("ca")) {
            try {
                createAlbum();
            } catch (NumberFormatException e) {
                System.out.println("\nGiven rating was not a number");

            } catch (NotInRatingRangeException e) {
                System.out.println("\nRating not in range of 0.0 to 10.0");

            }

        } else if (input.equalsIgnoreCase("ra")) {
            removeAlbum();
        } else if (input.equalsIgnoreCase("m")) {
            mergeAlbums();
        } else if (input.equalsIgnoreCase("atl")) {
            addToTrackList();
        } else if (input.equalsIgnoreCase("rtl")) {
            removeFromTrackList();
        }

    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving album category related creation and
    // removal
    public void processAlbumCategoryRelatedCreationAndRemoval(String input) {
        if (input.equalsIgnoreCase("cc")) {
            createCategory();
        } else if (input.equalsIgnoreCase("rc")) {
            removeCategory();
        } else if (input.equalsIgnoreCase("aac")) {
            addToCategory();
        } else if (input.equalsIgnoreCase("rac")) {
            removeFromCategory();
        }
    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving listing
    public void processListing(String input) {
        if (input.equalsIgnoreCase("l")) {
            printAllReviews();
        } else if (input.equalsIgnoreCase("lc")) {
            printAllCategories();
        }

    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving sorting
    public void processSorting(String input) {
        if (input.equalsIgnoreCase("saa")) {
            manager.sortAlbumsByAlphabeticalArtist();
            printAllReviews();
        } else if (input.equalsIgnoreCase("sna")) {
            manager.sortAlbumsByAlphabeticalName();
            printAllReviews();
        } else if (input.equalsIgnoreCase("sra")) {
            manager.sortAlbumsByRating();
            printAllReviews();
        }
    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving updating
    public void processUpdating(String input) {
        if (input.equalsIgnoreCase("uc")) {
            updateCategoryName();
        } else if (input.equalsIgnoreCase("ua")) {
            runUpdateSubMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: acts upon update related user input in sub-menu
    public void processUpdateSubMenuInput(String input) {
        if (input.equalsIgnoreCase("n")) {
            updateAlbumField(input);
        } else if (input.equalsIgnoreCase("a")) {
            updateAlbumField(input);
        } else if (input.equalsIgnoreCase("g")) {
            updateAlbumField(input);
        } else if (input.equalsIgnoreCase("r")) {
            updateAlbumField(input);
        } else if (input.equalsIgnoreCase("re")) {
            updateAlbumField(input);
        } else {
            System.out.println("\n\nNot a valid input");
        }
    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving loading and saving
    public void processLoadingAndSaving(String input) {
        if (input.equalsIgnoreCase("ld")) {
            loadReviewManager();
        } else if (input.equalsIgnoreCase("sv")) {
            saveReviewManager();
        }
    }

    // referenced from TellerApp
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    // EFFECTS: prints out main menu options
    public void showMainOptions() {
        System.out.println("\nOptions:");

        System.out.println("\tType e to exit the app\n");

        System.out.println("\tType ca to create an album review");
        System.out.println("\tType ra to remove an album review");
        System.out.println(
                "\tType m to merge the tracklists of two album reviews");
        System.out.println("\tType atl to add songs to the tracklist of an existing album review");
        System.out.println("\tType rtl to remove songs from the tracklist of an existing album review\n");

        System.out.println("\tType cc to create a category");
        System.out.println("\tType rc to remove a category");
        System.out.println("\tType aac to add an album to a category");
        System.out.println("\tType rac to remove an album from a category\n");

        System.out.println("\tType l to list all reviews");
        System.out.println("\tType lc to list all categories\n");

        System.out.println("\tType saa to sort all album reviews by artist alphabetical");
        System.out.println("\tType sna to sort all album reviews by name alphabetical");
        System.out.println("\tType sra to sort all album reviews by rating high to low\n");

        System.out.println("\tType uc to update a category's name");
        System.out.println("\tType ua to update an album review\n");
        System.out.println("\tType ld to load saved album reviews and categories");
        System.out.println("\tType sv to save album reviews and categories");

    }

    // referenced from TellerApp
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    // EFFECTS: prints out a sub-menu of options for updating album reviews
    public void showUpdatingReviewsOptions() {

        System.out.println("\nAlbum Review Updating Options:");

        System.out.println("\tType b to return to main menu\n");

        System.out.println("\tType n to update an album's name");
        System.out.println("\tType a to update an album's artist");
        System.out.println("\tType g to update an album's genre");
        System.out.println("\tType r to update an album's rating");
        System.out.println("\tType re to update an album's review");

    }

    // MODIFIES: this
    // EFFECTS: creates a new album with the given information and adds it to the
    // album list if there isn't already an album with the same name and artist.
    // throws an exception if given rating is not between 0.0 and 10.0 both
    // inclusive
    public void createAlbum() throws NotInRatingRangeException {
        System.out.println("Enter name of album");
        String name = scan.nextLine();

        System.out.println("Enter name of artist");
        String artist = scan.nextLine();

        System.out.println("Enter genre");
        String genre = scan.nextLine();

        System.out.println("Enter album rating (0.0 to 10.0)");
        double rating = Double.parseDouble(scan.nextLine());

        if (!(rating >= 0.0 && rating <= 10.0)) {
            throw new NotInRatingRangeException();

        }

        System.out.println("Enter review");
        String review = scan.nextLine();

        if (manager.getWantedAlbum(name, artist) == null) {
            manager.addAlbum(new Album(name, artist, genre, rating, review));
            System.out.println("\n\nAlbum created!");

        } else {
            System.out.println("\n\nAlbum already exists");
        }
    }

    // EFFECTS: takes user input to add songs to the tracklist of an album if the
    // song is not already in there
    public void addToTrackList() {
        System.out.println("Enter name of album to add to tracklist of");
        String name = scan.nextLine();

        System.out.println("Enter name of artist");
        String artist = scan.nextLine();

        Album albumToAddSongTo = manager.getWantedAlbum(name, artist);
        boolean addMoreSongs = true;

        if (albumToAddSongTo != null) {
            while (addMoreSongs) {
                try {
                    if (!promptUserToAddSongs(albumToAddSongTo)) {
                        addMoreSongs = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nGiven rating was not a number\n");
                } catch (NotInRatingRangeException e) {
                    System.out.println("\nRating not in range of 0.0 to 10.0\n");

                }

            }
        } else {
            System.out.println("\n\nAlbum not found");
        }

    }

    // EFFECTS: asks users for information to add songs to a tracklist if the song
    // is not already there. Returns true if user wants to add more songs, false
    // otherwise. Throws an exception if given rating is not between 0.0 and 10.0
    // both inclusive

    // this is a helper function for addToTrackList()
    public boolean promptUserToAddSongs(Album albumToAddSongTo) throws NotInRatingRangeException {
        System.out.println("Enter name of song");
        String songName = scan.nextLine();

        System.out.println("Enter name of artist");
        String artistName = scan.nextLine();

        System.out.println("Enter song rating (from 0 to 10)");
        double rating = Double.parseDouble(scan.nextLine());

        if (!(rating >= 0.0 && rating <= 10.0)) {
            throw new NotInRatingRangeException();

        }

        System.out.println("Enter review");
        String review = scan.nextLine();

        if (manager.getWantedSongInTracklist(songName, artistName, albumToAddSongTo) == null) {
            manager.addToAlbumTracklist(albumToAddSongTo, songName, artistName, rating, review);
            System.out.println("\n\nSong Added!");

        } else {
            System.out.println("\nSong already in tracklist");
        }
        System.out.println("\nkeep adding songs? (type n to stop, anything otherwise)");

        if (scan.nextLine().equalsIgnoreCase("n")) {
            return false;
        } else {
            return true;
        }

    }

    // EFFECTS: takes user input to remove songs to the tracklist of an album
    public void removeFromTrackList() {
        System.out.println("Enter name of album to remove songs from its tracklist");
        String name = scan.nextLine();

        System.out.println("Enter name of artist");
        String artist = scan.nextLine();

        Album wantedAlbum = manager.getWantedAlbum(name, artist);
        boolean removeMoreSongs = true;

        if (wantedAlbum != null) {
            while (removeMoreSongs) {
                try {
                    if (!promptUserToRemoveSongs(wantedAlbum, wantedAlbum.getTracklist().size())) {
                        removeMoreSongs = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nNot a number");

                }

            }
        } else {
            System.out.println("\n\nAlbum not found");
        }

    }

    // EFFECTS: asks users for information to remove songs from a tracklist and
    // returns true if user wants to add more songs, false otherwise

    // this is a helper function for addToTrackList()
    public boolean promptUserToRemoveSongs(Album albumToRemoveSongFrom, int tracklistSize) {
        System.out.println("Enter number of song in tracklist");
        Integer songNumber = Integer.parseInt(scan.nextLine());

        if (songNumber >= 1 && songNumber <= tracklistSize) {
            manager.removeFromAlbumTracklist(albumToRemoveSongFrom, songNumber);
            System.out.println("\n\nSong removed!");
        } else {
            System.out.println("\nNot a valid number");
        }

        System.out.println("\nkeep removing songs? (type n to stop, anything otherwise)");
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

        Album albumToRemove = manager.getWantedAlbum(name, artist);

        if (albumToRemove != null) {
            manager.removeAlbum(albumToRemove);
            System.out.println("\n\nAlbum removed!");

        } else {
            System.out.println("\n\nAlbum not found");
        }

    }

    // MODIFIES: this
    // EFFECTS: combines the two given album's tracklists together. The second album
    // is removed from albums list. Duplicate songs are not added to the merged
    // tracklist
    public void mergeAlbums() {
        System.out.println("Enter name of first album (this one will have the merged tracklist)");
        String name = scan.nextLine();

        System.out.println("Enter album artist name of first album");
        String artist = scan.nextLine();

        System.out.println("Enter name of second album (this album will be deleted)");
        String name2 = scan.nextLine();

        System.out.println("Enter album artist name of second album");
        String artist2 = scan.nextLine();

        Album firstAlbum = manager.getWantedAlbum(name, artist);
        Album secondAlbum = manager.getWantedAlbum(name2, artist2);

        if (firstAlbum != null && secondAlbum != null) {
            manager.getAlbumsList().get(manager.getIndexOfAlbum(firstAlbum))
                    .mergeAlbum(manager.getAlbumsList().get(manager.getIndexOfAlbum(secondAlbum)));
            manager.removeAlbum(secondAlbum);

            if (manager.albumIsInAnyCategory(secondAlbum)) {
                manager.removeFromAllCategories(name2, artist2);
            }
            System.out.println("\nAlbums merged!");

        } else {
            System.out.println("\nEither one of or both albums were not found");
        }
    }


    // MODIFIES: this
    // EFFECTS: creates a new category with the given name and adds it to the
    // categories list if it does not already exist
    public void createCategory() {
        System.out.println("Enter desired category name");
        String name = scan.nextLine();

        if (manager.getWantedCategory(name) == null) {
            manager.addCategory(new AlbumCategory(name));
            System.out.println("\nCategory created!");

        } else {
            System.out.println("\nCategory already exists");
        }

    }

    // MODIFIES: this
    // EFFECTS: removes the category with the given name from category list
    public void removeCategory() {
        System.out.println("Enter name of category to remove");
        String name = scan.nextLine();

        AlbumCategory categoryToRemove = manager.getWantedCategory(name);

        if (categoryToRemove != null) {
            manager.removeCategory(categoryToRemove);
            System.out.println("\nCategory removed!");

        } else {
            System.out.println("\nCategory not found");
        }

    }

    // MODIFIES: this
    // EFFECTS: add the given album to the category with the given name if
    // the album is not already there
    public void addToCategory() {
        System.out.println("Enter name of category to add to");
        String name = scan.nextLine();

        System.out.println("Enter name of album to add");
        String albumName = scan.nextLine();

        System.out.println("Enter name of artist of album to add");
        String artistName = scan.nextLine();

        AlbumCategory categoryToAddto = manager.getWantedCategory(name);
        Album wantedAlbum = manager.getWantedAlbum(albumName, artistName);

        if (categoryToAddto != null) {
            if (wantedAlbum != null) {
                if (manager.getWantedAlbumInWantedCategory(albumName, artistName, categoryToAddto) == null) {
                    manager.addToCategory(categoryToAddto, albumName, artistName);
                    System.out.println("\n\nAlbum added!");
                } else {
                    System.out.println("\n\nAlbum already in the category");
                }

            } else {
                System.out.println("\n\nAlbum not found. Is it created?");
            }
        } else {
            System.out.println("\n\nCategory not found. Is it created?");
        }

    }

    // MODIFIES: this
    // EFFECTS: remove the given album from the category with the given name if
    // they both exist
    public void removeFromCategory() {
        System.out.println("Enter name of category to remove from");
        String name = scan.nextLine();

        System.out.println("Enter name of album to remove");
        String albumName = scan.nextLine();

        System.out.println("Enter name of artist of album to remove");
        String artistName = scan.nextLine();

        AlbumCategory categoryToRemoveFrom = manager.getWantedCategory(name);
        Album wantedAlbum = manager.getWantedAlbum(albumName, artistName);

        if (categoryToRemoveFrom != null) {
            if (wantedAlbum != null) {
                manager.removeFromCategory(categoryToRemoveFrom, albumName, artistName);
                System.out.println("\n\nAlbum removed from category!");
            } else {
                System.out.println("\n\nAlbum not found. Is it created?");
            }
        } else {
            System.out.println("\n\nCategory not found. Is it created?");
        }
    }

    // EFFECTS: prints all reviews
    public void printAllReviews() {

        if (!manager.getAlbumsList().isEmpty()) {
            System.out.println("\nAlbums:\n");
            for (Album album : manager.getAlbumsList()) {
                System.out.println(album.toString());
                System.out.println("Tracklist:\n" + album.trackListToString() + "\n");

            }
        } else {
            System.out.println("\n\nYou have no reviews!");
        }

    }

    // EFFECTS: prints out name of all categories and the info about the albums
    // in them
    public void printAllCategories() {
        System.out.println("\n");

        if (!manager.getAlbumCategoriesList().isEmpty()) {
            System.out.println("Categories:\n");

            for (AlbumCategory category : manager.getAlbumCategoriesList()) {
                System.out.println(category.getName() + ":\n");
                System.out.println(category.albumListToString());
                System.out.println("\n");
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
    // EFFECTS: updates the name of a category given by oldName to newName if the
    // category exists
    public void updateCategoryName() {

        System.out.println("Enter name of category to change");
        String oldName = scan.nextLine();

        System.out.println("Enter new name");
        String newName = scan.nextLine();

        if (manager.getWantedCategory(oldName) != null) {
            for (int i = 0; i < manager.getAlbumCategoriesList().size(); i++) {
                AlbumCategory currentCategory = manager.getAlbumCategoriesList().get(i);
                if (currentCategory.getName().equals(oldName)) {
                    currentCategory.setName(newName);
                    break;
                }
            }
            System.out.println("\n\nCategory updated!");
        } else {
            System.out.println("\n\nCategory not found!");
        }

    }

    // MODIFIES: this
    // EFFECTS: updates a given album field (name, artist, genre, rating, review,
    // tracklist) to a new given value if it exists
    public void updateAlbumField(String field) {
        System.out.println("Enter name of album to update");
        String name = scan.nextLine();

        System.out.println("Enter name of artist of album to update");
        String artist = scan.nextLine();

        if (field.equalsIgnoreCase("n")) {
            updateNameField(name, artist);

        } else if (field.equalsIgnoreCase("a")) {
            updateArtistField(name, artist);

        } else if (field.equalsIgnoreCase("g")) {
            updateGenreField(name, artist);

        } else if (field.equalsIgnoreCase("r")) {
            try {
                updateRatingField(name, artist);

            } catch (NumberFormatException e) {
                System.out.println("\nGiven rating was not a number");

            } catch (NotInRatingRangeException e) {
                System.out.println("\nRating was not in range of 0.0 to 10.0");

            }

        } else if (field.equalsIgnoreCase("re")) {
            updateReviewField(name, artist);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the name field of a given album (referenced by name and
    // artist) with the given new value if given album exists
    public void updateNameField(String albumName, String artist) {

        System.out.println("Enter new name");
        String newName = scan.nextLine();

        if (manager.getWantedAlbum(albumName, artist) != null) {
            manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(albumName, artist)))
                    .setName(newName);
            System.out.println("\n\nAlbum updated!");
        } else {
            System.out.println("\n\nAlbum not found!");
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the artist field of a given album (referenced by name and
    // artist) with the given new value if given album exists
    public void updateArtistField(String albumName, String artist) {
        System.out.println("Enter new artist");
        String newArtist = scan.nextLine();

        if (manager.getWantedAlbum(albumName, artist) != null) {
            manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(albumName, artist)))
                    .setArtist(newArtist);
            System.out.println("\n\nAlbum updated!");
        } else {
            System.out.println("\n\nAlbum not found!");
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the genre field of a given album (referenced by name and
    // artist) with the given new value if given album exists
    public void updateGenreField(String albumName, String artist) {
        System.out.println("Enter new genre");
        String newGenre = scan.nextLine();

        if (manager.getWantedAlbum(albumName, artist) != null) {
            manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(albumName, artist)))
                    .setGenre(newGenre);
            System.out.println("\n\nAlbum updated!");
        } else {
            System.out.println("\n\nAlbum not found!");
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the rating field of a given album (referenced by name and
    // artist) with the given new value if given album exists
    public void updateRatingField(String albumName, String artist) throws NotInRatingRangeException {
        System.out.println("Enter new rating (0.0 to 10.0)");
        Double newRating = Double.parseDouble(scan.nextLine());

        if (!(newRating >= 0.0 && newRating <= 10.0)) {
            throw new NotInRatingRangeException();
        }

        if (manager.getWantedAlbum(albumName, artist) != null) {
            manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(albumName, artist)))
                    .setRating(newRating);
        } else {
            System.out.println("\n\nAlbum not found!");
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the review field of a given album (referenced by name and
    // artist) with the given new value if given album exists
    public void updateReviewField(String albumName, String artist) {

        System.out.println("Enter new review");
        String newReview = scan.nextLine();

        if (manager.getWantedAlbum(albumName, artist) != null) {
            manager.getAlbumsList().get(manager.getIndexOfAlbum(manager.getWantedAlbum(albumName, artist)))
                    .setReview(newReview);
            System.out.println("\n\nAlbum updated!");
        } else {
            System.out.println("\n\nAlbum not found!");
        }

    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // MODIFIES: this
    // EFFECTS: loads review manager from file
    public void loadReviewManager() {
        try {
            manager = jsonReader.readReviewManager();
            System.out.println("\nLoaded reviews and categories from" + JSON_SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Error: failed to read from file: " + JSON_SAVE_FILE);
        }

    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // MODIFIES: this
    // EFFECTS: saves review manager to file
    public void saveReviewManager() {
        try {
            jsonWriter.open();
            jsonWriter.writeReviewManager(manager);
            jsonWriter.close();
            System.out.println("\nSaved reviews and categories to " + JSON_SAVE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Error: failed to write to file: " + JSON_SAVE_FILE);
        }

    }
}
