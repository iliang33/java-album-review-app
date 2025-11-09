package ui.tabs;

import javax.swing.JButton;

import exceptions.NotInRatingRangeException;
import model.Album;
import model.ReviewManager;
import ui.ButtonNames;
import ui.Prompts;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the albums tab on the navbar of the GUI where 
// album creation, removal, listing and sorting is done

public class AlbumsTab extends Tab {

    // EFFECTS: creates an albums tab displaying all album reviews and sidebar
    // containing buttons related to album reviews operations
    public AlbumsTab(ReviewManager manager) {
        super(manager);
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
            String name = getUserInput(Prompts.ALBUM_NAME.getValue());
            String artist = getUserInput(Prompts.ARTIST.getValue());
            String genre = getUserInput(Prompts.GENRE.getValue());
            try {
                Double rating = Double.parseDouble(getUserInput(Prompts.RATING.getValue()));

                if (!(rating >= 0.0 && rating <= 10.0)) {
                    throw new NotInRatingRangeException();
                }

                String review = getUserInput(Prompts.REVIEW.getValue());

                if (manager.getWantedAlbum(name, artist) == null) {
                    Album newAlbum = new Album(name, artist, genre, rating, review);
                    manager.addAlbum(newAlbum);

                } else {
                    showErrorMessage(this, "Album already exists");
                }

            } catch (NumberFormatException excpetion) {
                showErrorMessage(this, "Given rating was not a number");

            } catch (NotInRatingRangeException | NullPointerException exception) {
                showErrorMessage(this, "Rating not in range of 0.0 to 10.0");

            }

        });

        addToSidebar(button);

    }

    // EFFECTS: creates a remove review button that when clicked, prompts the user
    // for a review and removes it
    private void createRemoveReviewButton() {
        JButton button = createButton(ButtonNames.REMOVE_ALBUM.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            String name = getUserInput(Prompts.ALBUM_NAME.getValue());
            String artist = getUserInput(Prompts.ARTIST.getValue());

            Album albumToRemove = manager.getWantedAlbum(name, artist);

            if (albumToRemove != null) {
                manager.removeAlbum(albumToRemove);
                

            } else {
                showErrorMessage(this, "Album not found");
            }

        });

        addToSidebar(button);
    }

    // EFFECTS: creates a merge reviews button that when clicked, prompts the user
    // for two albums, and merges the tracklists together
    private void createMergeReviewButton() {
        JButton button = createButton(ButtonNames.MERGE.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);
        

    }

    // EFFECTS: creates an add tracklist button that when clicked, prompts the user
    // for an album and songs, then adds the songs to the album's tracklist
    private void createAddToTracklistButton() {
        JButton button = createButton(ButtonNames.ADD_TO_TRACKLIST.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates a remove tracklist button that when clicked, prompts the
    // user
    // for an album and song, then removes the songs from the album's tracklist
    private void createRemoveFromTracklistButton() {
        JButton button = createButton(ButtonNames.REMOVE_FROM_TRACKLIST.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates a sort by album name alphabetical button that when clicked,
    // displays all albums sorted by artist alphabetical
    private void createSortByArtistAlphaButton() {
        JButton button = createButton(ButtonNames.SORT_ARTIST.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates a sort by album name alphabetical button that when clicked,
    // displays all albums sorted by album name alphabetical
    private void createSortByNameAlphaButton() {
        JButton button = createButton(ButtonNames.SORT_NAME.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates a sort by rating button that when clicked,
    // displays all albums sorted by rating high to low
    private void createSortByRatingButton() {
        JButton button = createButton(ButtonNames.SORT_RATING.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

}