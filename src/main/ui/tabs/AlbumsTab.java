package ui.tabs;

import model.ReviewManager;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the albums tab on the navbar of the GUI where 
// album creation, removal, listing and sorting is done

public class AlbumsTab extends Tab {

    // EFFECTS: creates an albums tab displaying all album reviews and sidebar
    // containing buttons related to album reviews operations
    public AlbumsTab(ReviewManager manager) {
        super(manager);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons related to album review operations to the sidebar
    private void addButtonsToSidebar() {

    }

    // EFFECTS: displays all created albums reviews
    private void showCreatedReviews() {

    }

    // EFFECTS: creates an add review button that when clicked, prompts the user for
    // album info
    // and creates a new review
    private void createAddReviewButton() {

    }

    // EFFECTS: creates a remove review button that when clicked, prompts the user
    // for a review and removes it
    private void createRemoveReviewButton() {

    }

    // EFFECTS: creates a merge reviews button that when clicked, prompts the user
    // for two albums, and merges the tracklists together
    private void createMergeReviewButton() {

    }

    // EFFECTS: creates an add tracklist button that when clicked, prompts the user
    // for an album and songs, then adds the songs to the album's tracklist
    private void createAddToTracklistButton() {

    }

    // EFFECTS: creates a remove tracklist button that when clicked, prompts the
    // user
    // for an album and song, then removes the songs from the album's tracklist
    private void createRemoveFromTracklistButton() {

    }

    // EFFECTS: creates a sort by album name alphabetical button that when clicked,
    // displays all albums sorted by album name alphabetical
    private void createSortByNameAlphaButton() {

    }

    // EFFECTS: creates a sort by rating button that when clicked,
    // displays all albums sorted by rating high to low
    private void createSortByRatingButton() {

    }

}