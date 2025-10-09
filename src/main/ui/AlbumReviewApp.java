package ui;

import model.Album;
import model.AlbumCategory;
import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.NotInRatingRangeException;

// Album Review Application
public class AlbumReviewApp {

    private List<AlbumCategory> categories;
    private List<Album> albums;
    private Scanner scan;
    private boolean validInput;

    // EFFECTS: runs the application, and initializes valid input and both the
    // scanner and the lists used to track album categories and albums,
    public AlbumReviewApp() {
        this.categories = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.scan = new Scanner(System.in);
        this.validInput = false;

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

            if (input.equalsIgnoreCase("e")) {
                hasExit = true;
                System.out.println("\nApp Exited");
            } else {
                processMainInput(input);
            }

        }
    }

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

    // MODIFIES: this
    // EFFECTS: acts upon main menu user input
    public void processMainInput(String input) {
        processAlbumRelatedCreationAndRemoval(input);
        processAlbumCategoryRelatedCreationAndRemoval(input);
        processListing(input);
        processSorting(input);
        processUpdating(input);

        if (!this.validInput) {
            System.out.println("\n\nNot a valid input");

        }

    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving album related creation and removal
    public void processAlbumRelatedCreationAndRemoval(String input) {
        if (input.equalsIgnoreCase("ca")) {
            this.validInput = true;
            try {
                createAlbum();
            } catch (NumberFormatException e) {
                System.out.println("\nGiven rating was not a number");

            } catch (NotInRatingRangeException e) {
                System.out.println("\nRating not in range of 0.0 to 10.0");

            }

        } else if (input.equalsIgnoreCase("ra")) {
            this.validInput = true;
            removeAlbum();
        } else if (input.equalsIgnoreCase("atl")) {
            this.validInput = true;
            addToTrackList();
        } else if (input.equalsIgnoreCase("rtl")) {
            this.validInput = true;
            removeFromTrackList();
        }

    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving album category related creation and
    // removal
    public void processAlbumCategoryRelatedCreationAndRemoval(String input) {
        if (input.equalsIgnoreCase("cc")) {
            this.validInput = true;
            createCategory();
        } else if (input.equalsIgnoreCase("rc")) {
            this.validInput = true;
            removeCategory();
        } else if (input.equalsIgnoreCase("aac")) {
            this.validInput = true;
            addToCategory();
        } else if (input.equalsIgnoreCase("rac")) {
            this.validInput = true;
            removeFromCategory();
        }
    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving listing
    public void processListing(String input) {
        if (input.equalsIgnoreCase("l")) {
            this.validInput = true;
            printAllReviews();
        } else if (input.equalsIgnoreCase("lc")) {
            this.validInput = true;
            printAllCategories();
        }

    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving sorting
    public void processSorting(String input) {
        if (input.equalsIgnoreCase("saa")) {
            this.validInput = true;
            sortAlbumsByAlphabeticalArtist();
        } else if (input.equalsIgnoreCase("sna")) {
            this.validInput = true;
            sortAlbumsByAlphabeticalName();
        } else if (input.equalsIgnoreCase("sra")) {
            this.validInput = true;
            sortAlbumsByRating();
        }
    }

    // MODIFIES: this
    // EFFECTS: acts upon input involving updating
    public void processUpdating(String input) {
        if (input.equalsIgnoreCase("uc")) {
            this.validInput = true;
            updateCategoryName();
        } else if (input.equalsIgnoreCase("ua")) {
            this.validInput = true;
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

    // EFFECTS: prints out main menu options
    public void showMainOptions() {
        System.out.println("\nOptions:");

        System.out.println("\tType e to exit the app\n");

        System.out.println("\tType ca to create an album review");
        System.out.println("\tType ra to remove an album review");
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
        System.out.println("\tType ua to update an album review");

    }

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

        if (getIndexOfWantedAlbum(name, artist) == -1) {
            this.albums.add(new Album(name, artist, genre, rating, review));
            System.out.println("\n\nAlbum created!");

        } else {
            System.out.println("\n\nAlbum already exists");
        }
    }

    // EFFECTS: takes user input to add songs to the tracklist of an album if the
    // song is not already in there
    public void addToTrackList() {
        System.out.println("Enter name of album to add tracklist to");
        String name = scan.nextLine();

        System.out.println("Enter name of artist");
        String artist = scan.nextLine();

        int indexOfAlbumToAddSongTo = getIndexOfWantedAlbum(name, artist);
        boolean addMoreSongs = true;

        if (indexOfAlbumToAddSongTo != -1) {
            while (addMoreSongs) {
                try {
                    if (!promptUserToAddSongs(indexOfAlbumToAddSongTo)) {
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
    public boolean promptUserToAddSongs(int indexOfAlbumToAddSongTo) throws NotInRatingRangeException {
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

        if (getIndexOfWantedSongInAlbumTracklist(songName, indexOfAlbumToAddSongTo) == -1) {
            this.albums.get(indexOfAlbumToAddSongTo).addSong(new Song(songName, artistName, rating, review));
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

        int indexOfAlbum = getIndexOfWantedAlbum(name, artist);
        boolean removeMoreSongs = true;

        if (indexOfAlbum != -1) {
            while (removeMoreSongs) {
                try {
                    if (!promptUserToRemoveSongs(indexOfAlbum, this.albums.get(indexOfAlbum).getTracklist().size())) {
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
    public boolean promptUserToRemoveSongs(int indexOfAlbumToRemoveSongFrom, int tracklistSize) {
        System.out.println("Enter number of song in tracklist");
        Integer songNumber = Integer.parseInt(scan.nextLine());

        if (songNumber >= 1 && songNumber <= tracklistSize) {
            this.albums.get(indexOfAlbumToRemoveSongFrom).getTracklist().remove(songNumber - 1);
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

        int indexOfAlbumToRemove = getIndexOfWantedAlbum(name, artist);

        if (indexOfAlbumToRemove != -1) {
            this.albums.remove(indexOfAlbumToRemove);
            System.out.println("\n\nAlbum removed!");

        } else {
            System.out.println("\n\nAlbum not found");
        }

    }

    // MODIFIES: this
    // EFFECTS: creates a new category with the given name and adds it to the
    // categories list if it does not already exist
    public void createCategory() {
        System.out.println("Enter desired category name");
        String name = scan.nextLine();

        if (getIndexOfWantedCategory(name) == -1) {
            this.categories.add(new AlbumCategory(name));
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

        int indexOfCategoryToRemove = getIndexOfWantedCategory(name);

        if (indexOfCategoryToRemove != -1) {
            this.categories.remove(indexOfCategoryToRemove);
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

        int indexOfCategoryToAddto = getIndexOfWantedCategory(name);
        int indexOfAlbum = getIndexOfWantedAlbum(albumName, artistName);

        if (indexOfCategoryToAddto != -1) {
            if (indexOfAlbum != -1) {
                if (getIndexOfWantedAlbumInCategory(albumName, artistName, indexOfCategoryToAddto) == -1) {
                    this.categories.get(indexOfCategoryToAddto).addAlbum(this.albums.get(indexOfAlbum));
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

        int indexOfCategoryToRemoveFrom = getIndexOfWantedCategory(name);
        int indexOfAlbum = getIndexOfWantedAlbum(albumName, artistName);

        if (indexOfCategoryToRemoveFrom != -1) {
            if (indexOfAlbum != -1) {
                this.categories.get(indexOfCategoryToRemoveFrom).removeAlbum(albumName, artistName);
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
        System.out.println("\n");

        if (!this.albums.isEmpty()) {
            System.out.println("Albums:\n");
            for (Album album : albums) {
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

        if (!this.categories.isEmpty()) {
            System.out.println("Categories:\n");

            for (AlbumCategory category : categories) {
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
    // EFFECTS: sorts album list by artist alphabetically then prints out the sorted
    // album list. If two albums have the same artist, the album added first is
    // shown first
    public void sortAlbumsByAlphabeticalArtist() {
        for (int i = 0; i < this.albums.size(); i++) {
            for (int j = 0; j < this.albums.size(); j++) {
                // a negative result from compareTo means the string on the left should go
                // before the string on the right
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
    // EFFECTS: sorts album list by name alphabetically then prints out the sorted
    // album list. if two albums have the same name, the one added first is shown
    // first
    public void sortAlbumsByAlphabeticalName() {
        for (int i = 0; i < this.albums.size(); i++) {
            for (int j = 0; j < this.albums.size(); j++) {
                // a negative result from compareTo means the string on the left should go
                // before the string on the right
                if (this.albums.get(i).getName().compareToIgnoreCase(this.albums.get(j).getName()) < 0) {
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
    // EFFECTS: sorts album list by rating high to low. if two albums have same
    // rating, the album added first is shown first
    public void sortAlbumsByRating() {
        for (int i = 0; i < this.albums.size(); i++) {
            for (int j = 0; j < this.albums.size(); j++) {

                if (this.albums.get(i).getRating() > this.albums.get(j).getRating()) {
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
    // EFFECTS: updates the name of a category given by oldName to newName if the
    // category exists
    public void updateCategoryName() {

        System.out.println("Enter name of category to change");
        String oldName = scan.nextLine();

        System.out.println("Enter new name");
        String newName = scan.nextLine();

        if (getIndexOfWantedCategory(oldName) != -1) {
            for (int i = 0; i < this.categories.size(); i++) {
                AlbumCategory currentCategory = this.categories.get(i);
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

        boolean foundAlbumInACategory = false;

        for (int i = 0; i < this.categories.size(); i++) {
            for (int j = 0; j < this.categories.get(i).getAlbumList().size(); j++) {
                Album currentAlbum = this.categories.get(i).getAlbumList().get(j);
                if (currentAlbum.getName().equalsIgnoreCase(albumName)) {
                    currentAlbum.setName(newName);
                    foundAlbumInACategory = true;
                    System.out.println("\n\nAlbum updated!");
                    break;
                }
            }
        }
        // since an album has to be created before it is added to a category
        // if it is in a category, it must be in the list of all albums
        if (!foundAlbumInACategory) {
            if (getIndexOfWantedAlbum(albumName, artist) != -1) {
                this.albums.get(getIndexOfWantedAlbum(albumName, artist)).setName(newName);
                System.out.println("\n\nAlbum updated!");
            } else {
                System.out.println("\n\nAlbum not found!");
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the artist field of a given album (referenced by name and
    // artist) with the given new value if given album exists

    public void updateArtistField(String albumName, String artist) {
        System.out.println("Enter new artist");
        String newArtist = scan.nextLine();

        boolean foundAlbumInACategory = false;

        for (int i = 0; i < this.categories.size(); i++) {
            for (int j = 0; j < this.categories.get(i).getAlbumList().size(); j++) {
                Album currentAlbum = this.categories.get(i).getAlbumList().get(j);
                if (currentAlbum.getName().equalsIgnoreCase(albumName)) {
                    currentAlbum.setArtist(newArtist);
                    foundAlbumInACategory = true;
                    System.out.println("\n\nAlbum updated!");
                    break;
                }
            }
        }
        // since an album has to be created before it is added to a category
        // if it is in a category, it must be in the list of all albums
        if (!foundAlbumInACategory) {
            if (getIndexOfWantedAlbum(albumName, artist) != -1) {
                this.albums.get(getIndexOfWantedAlbum(albumName, artist)).setArtist(newArtist);
                System.out.println("\n\nAlbum updated!");
            } else {
                System.out.println("\n\nAlbum not found!");
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the genre field of a given album (referenced by name and
    // artist) with the given new value if given album exists

    public void updateGenreField(String albumName, String artist) {
        System.out.println("Enter new genre");
        String newGenre = scan.nextLine();

        boolean foundAlbumInACategory = false;

        for (int i = 0; i < this.categories.size(); i++) {
            for (int j = 0; j < this.categories.get(i).getAlbumList().size(); j++) {
                Album currentAlbum = this.categories.get(i).getAlbumList().get(j);
                if (currentAlbum.getName().equalsIgnoreCase(albumName)) {
                    currentAlbum.setGenre(newGenre);
                    foundAlbumInACategory = true;
                    System.out.println("\n\nAlbum updated!");
                    break;
                }
            }
        }
        // since an album has to be created before it is added to a category
        // if it is in a category, it must be in the list of all albums
        if (!foundAlbumInACategory) {
            if (getIndexOfWantedAlbum(albumName, artist) != -1) {
                this.albums.get(getIndexOfWantedAlbum(albumName, artist)).setGenre(newGenre);
                System.out.println("\n\nAlbum updated!");
            } else {
                System.out.println("\n\nAlbum not found!");
            }
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

        boolean foundAlbumInACategory = false;

        for (int i = 0; i < this.categories.size(); i++) {
            for (int j = 0; j < this.categories.get(i).getAlbumList().size(); j++) {
                Album currentAlbum = this.categories.get(i).getAlbumList().get(j);
                if (currentAlbum.getName().equalsIgnoreCase(albumName)) {
                    currentAlbum.setRating(newRating);
                    foundAlbumInACategory = true;
                    break;
                }
            }
        }
        // since an album has to be created before it is added to a category
        // if it is in a category, it must be in the list of all albums
        if (!foundAlbumInACategory) {
            if (getIndexOfWantedAlbum(albumName, artist) != -1) {
                this.albums.get(getIndexOfWantedAlbum(albumName, artist)).setRating(newRating);
            } else {
                System.out.println("\n\nAlbum not found!");
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the review field of a given album (referenced by name and
    // artist) with the given new value if given album exists

    public void updateReviewField(String albumName, String artist) {

        System.out.println("Enter new review");
        String newReview = scan.nextLine();

        boolean foundAlbumInACategory = false;

        for (int i = 0; i < this.categories.size(); i++) {
            for (int j = 0; j < this.categories.get(i).getAlbumList().size(); j++) {
                Album currentAlbum = this.categories.get(i).getAlbumList().get(j);
                if (currentAlbum.getName().equalsIgnoreCase(albumName)) {
                    currentAlbum.setReview(newReview);
                    foundAlbumInACategory = true;
                    System.out.println("\n\nAlbum updated!");
                    break;
                }
            }
        }
        // since an album has to be created before it is added to a category
        // if it is in a category, it must be in the list of all albums
        if (!foundAlbumInACategory) {
            if (getIndexOfWantedAlbum(albumName, artist) != -1) {
                this.albums.get(getIndexOfWantedAlbum(albumName, artist)).setReview(newReview);
                System.out.println("\n\nAlbum updated!");
            } else {
                System.out.println("\n\nAlbum not found!");
            }
        }

    }

    // EFFECTS: return the index of the album in albums list specified by name and
    // artist. returns -1 if not found
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

    // EFFECTS: return the index of the album (referenced by name and artist) in the
    // given category (referenced by name). returns -1 if not found
    public int getIndexOfWantedAlbumInCategory(String name, String artist, int indexOfWantedCategory) {

        int indexOfWantedAlbum = -1;
        for (int i = 0; i < this.categories.get(indexOfWantedCategory).getAlbumList().size(); i++) {
            Album currentAlbum = this.categories.get(indexOfWantedCategory).getAlbumList().get(i);
            if (currentAlbum.getName().equalsIgnoreCase(name) && currentAlbum.getArtist().equalsIgnoreCase(artist)) {
                indexOfWantedAlbum = i;
            }

        }
        return indexOfWantedAlbum;

    }

    // EFFECTS: return the index of the category in albums list specified by name.
    // returns -1 if not found
    public int getIndexOfWantedCategory(String name) {

        int indexOfWantedCategory = -1;
        for (int i = 0; i < this.categories.size(); i++) {
            AlbumCategory currentCategory = this.categories.get(i);
            if (currentCategory.getName().equals(name)) {
                indexOfWantedCategory = i;
            }

        }
        return indexOfWantedCategory;

    }

    // EFFECTS: return the index of the song (referenced by song name) in the given
    // album's (referenced by album index) tracklist. returns -1 if not found
    public int getIndexOfWantedSongInAlbumTracklist(String name, int indexOfAlbum) {

        int indexOfWantedSong = -1;
        for (int i = 0; i < this.albums.get(indexOfAlbum).getTracklist().size(); i++) {
            Song currentSong = this.albums.get(indexOfAlbum).getTracklist().get(i);
            if (currentSong.getName().equalsIgnoreCase(name)) {
                indexOfWantedSong = i;
            }
        }
        return indexOfWantedSong;

    }

}
