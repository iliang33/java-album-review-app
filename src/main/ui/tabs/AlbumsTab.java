package ui.tabs;

import javax.swing.JButton;

import exceptions.NotInRatingRangeException;
import exceptions.PopUpClosedOrCancelledException;
import model.Album;
import ui.ButtonNames;
import ui.ErrorMessages;
import ui.Prompts;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the albums tab on the navbar of the GUI where 
// album creation, removal, listing and sorting is done

public class AlbumsTab extends Tab {

    // EFFECTS: creates an albums tab displaying all album reviews and sidebar
    // containing buttons related to album reviews operations
    public AlbumsTab() {
        super();
        createSidebar();
        addButtonsToSidebar();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons related to album review operations to the sidebar
    private void addButtonsToSidebar() {
        createAddReviewButton();
        createRemoveReviewButton();
        createMergeReviewButton();
        createAddToTracklistButton();
        createRemoveFromTracklistButton();
        createSortByArtistAlphaButton();
        createSortByNameAlphaButton();
        createSortByRatingButton();

    }

    // EFFECTS: creates an add review button that when clicked, prompts the user for
    // album info and creates a new review
    private void createAddReviewButton() {
        JButton button = createButton(ButtonNames.ADD_ALBUM.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {

            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());
                String genre = getUserInput(Prompts.GENRE.getValue());
                Double rating = Double.parseDouble(getUserInput(Prompts.RATING.getValue()));

                if (!(rating >= 0.0 && rating <= 10.0)) {
                    throw new NotInRatingRangeException();
                }

                rating = Math.round(rating * 10.0) / 10.0;

                String review = getUserInput(Prompts.REVIEW.getValue());

                if (manager.getWantedAlbum(name, artist) == null) {
                    manager.addAlbum(new Album(name, artist, genre, rating, review));

                } else {
                    showErrorMessage(this, ErrorMessages.DUPLICATE_ALBUM.getValue());
                }

            } catch (NumberFormatException | NotInRatingRangeException exception) {
                showErrorMessage(this, ErrorMessages.INVALID.getValue());

            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing

            }

        });

        addToSidebar(button);

    }

    // EFFECTS: creates a remove review button that when clicked, prompts the user
    // for a review and removes it
    private void createRemoveReviewButton() {
        JButton button = createButton(ButtonNames.REMOVE_ALBUM.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());

                Album albumToRemove = manager.getWantedAlbum(name, artist);

                if (albumToRemove != null) {
                    manager.removeAlbum(albumToRemove);

                    if (manager.albumIsInAnyCategory(albumToRemove)) {
                        manager.removeFromAllCategories(name, artist);
                    }

                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (PopUpClosedOrCancelledException except) {
                // do nothing

            }

        });

        addToSidebar(button);
    }

    // EFFECTS: creates a merge reviews button that when clicked, prompts the user
    // for two albums, and merges the tracklists together
    private void createMergeReviewButton() {
        JButton button = createButton(ButtonNames.MERGE.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            try {
                String name = getUserInput(Prompts.FIRST_ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.FIRST_ARTIST.getValue());

                String name2 = getUserInput(Prompts.SECOND_ALBUM_NAME.getValue());
                String artist2 = getUserInput(Prompts.SECOND_ARTIST.getValue());

                Album firstAlbum = manager.getWantedAlbum(name, artist);
                Album secondAlbum = manager.getWantedAlbum(name2, artist2);

                if (firstAlbum != null && secondAlbum != null) {
                    manager.getAlbumsList().get(manager.getIndexOfAlbum(firstAlbum))
                            .mergeAlbum(manager.getAlbumsList().get(manager.getIndexOfAlbum(secondAlbum)));
                    manager.removeAlbum(secondAlbum);

                    if (manager.albumIsInAnyCategory(secondAlbum)) {
                        manager.removeFromAllCategories(name2, artist2);
                    }

                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (PopUpClosedOrCancelledException except) {
                // do nothing

            }
        });

        addToSidebar(button);

    }

    // EFFECTS: creates an add tracklist button that when clicked, prompts the user
    // for an album and songs, then adds the songs to the album's tracklist
    private void createAddToTracklistButton() {
        JButton button = createButton(ButtonNames.ADD_TO_TRACKLIST.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            try {

                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());

                Album albumToAddSongTo = manager.getWantedAlbum(name, artist);
                boolean addMoreSongs = true;

                if (albumToAddSongTo != null) {
                    while (addMoreSongs) {
                        if (!promptUserToAddSongs(albumToAddSongTo)) {
                            addMoreSongs = false;
                        }

                    }
                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());

                }
            } catch (PopUpClosedOrCancelledException except) {
                // do nothing

            }
        });

        addToSidebar(button);

    }

    // EFFECTS: asks users for information to add songs to a tracklist if the song
    // is not already there. Returns true if user wants to add more songs, false
    // otherwise. Throws an exception if given rating is not between 0.0 and 10.0
    // both inclusive and handles it with an error message

    // this is a helper function for createAddToTracklistButton()
    public boolean promptUserToAddSongs(Album albumToAddSongTo) {

        try {
            String songName = getUserInput(Prompts.SONG.getValue());
            String artistName = getUserInput(Prompts.ARTIST.getValue());
            Double rating = Double.parseDouble(getUserInput(Prompts.RATING.getValue()));

            if (!(rating >= 0.0 && rating <= 10.0)) {
                throw new NotInRatingRangeException();
            }

            rating = Math.round(rating * 10.0) / 10.0;

            String review = getUserInput(Prompts.REVIEW.getValue());

            if (manager.getWantedSongInTracklist(songName, artistName, albumToAddSongTo) == null) {
                manager.addToAlbumTracklist(albumToAddSongTo, songName, artistName, rating, review);

            } else {
                showErrorMessage(this, ErrorMessages.DUPLICATE_SONG.getValue());
            }

            if (getUserConfirmation(this, Prompts.CONTINUE.getValue()) == 0) { // 0 means user clicked yes
                return true;
            }
        } catch (NumberFormatException | NotInRatingRangeException exception) {
            showErrorMessage(this, ErrorMessages.INVALID.getValue());

        } catch (PopUpClosedOrCancelledException exception) {
            // do nothing

        }

        return false;

    }

    // EFFECTS: creates a remove tracklist button that when clicked, prompts the
    // user for an album and song, then removes the songs from the album's tracklist
    private void createRemoveFromTracklistButton() {
        JButton button = createButton(ButtonNames.REMOVE_FROM_TRACKLIST.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            try {
                String name = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artist = getUserInput(Prompts.ARTIST.getValue());

                Album wantedAlbum = manager.getWantedAlbum(name, artist);
                boolean removeMoreSongs = true;

                if (wantedAlbum != null) {
                    while (removeMoreSongs) {
                        if (!promptUserToRemoveSongs(wantedAlbum, wantedAlbum.getTracklist().size())) {
                            removeMoreSongs = false;
                        }
                    }
                } else {
                    showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                }
            } catch (PopUpClosedOrCancelledException except) {
                // do nothing
            }
        });
        addToSidebar(button);
    }

    // EFFECTS: asks users for information to remove songs from a tracklist and
    // returns true if user wants to add more songs, false otherwise

    // this is a helper function for createRemoveFromTracklistButton()
    public boolean promptUserToRemoveSongs(Album albumToRemoveSongFrom, int tracklistSize) {

        try {
            Integer songNumber = Integer.parseInt(getUserInput(Prompts.SONG_NUMBER.getValue()));

            if (songNumber >= 1 && songNumber <= tracklistSize) {
                manager.removeFromAlbumTracklist(albumToRemoveSongFrom, songNumber);
            } else {
                showErrorMessage(this, ErrorMessages.NOT_A_SONG_NUMBER.getValue());
            }

        } catch (NumberFormatException except) {
            showErrorMessage(this, ErrorMessages.NOT_A_NUM.getValue());
        } catch (PopUpClosedOrCancelledException exception) {
            // do nothing

        }

        int confirmation = getUserConfirmation(this, Prompts.CONTINUE.getValue());

        if (confirmation == 0) { // 0 means user clicked yes
            return true;
        } else {
            return false;
        }

    }

    // EFFECTS: creates a sort by album name alphabetical button that when clicked,
    // displays all albums sorted by artist alphabetical
    private void createSortByArtistAlphaButton() {
        JButton button = createButton(ButtonNames.SORT_ARTIST.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            manager.sortAlbumsByAlphabeticalArtist();
        });

        addToSidebar(button);

    }

    // EFFECTS: creates a sort by album name alphabetical button that when clicked,
    // displays all albums sorted by album name alphabetical
    private void createSortByNameAlphaButton() {
        JButton button = createButton(ButtonNames.SORT_NAME.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            manager.sortAlbumsByAlphabeticalName();
        });
        addToSidebar(button);

    }

    // EFFECTS: creates a sort by rating button that when clicked,
    // displays all albums sorted by rating high to low
    private void createSortByRatingButton() {
        JButton button = createButton(ButtonNames.SORT_RATING.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            manager.sortAlbumsByRating();
        });
        addToSidebar(button);

    }

}