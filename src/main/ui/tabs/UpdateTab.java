package ui.tabs;

import model.ReviewManager;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the update tab on the navbar of the GUI where 
// updating existing album review info is done

public class UpdateTab extends Tab {

    // EFFECTS: creates an update tab displaying all albums reviews and a sidebar
    // containing buttons related to updating album review fields
    public UpdateTab(ReviewManager manager) {
        super(manager);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons related to updating album review operations to the
    // sidebar
    private void addButtonsToSidebar() {

    }

    // EFFECTS: displays all created albums reviews
    private void showCreatedReviews() {

    }

    // EFFECTS: prompts user for an album and new name, then updates that album to
    // have the new name
    private void createUpdateNameButton() {

    }

    // EFFECTS: prompts user for an album and new artist, then updates that album to
    // have the new artist
    private void createUpdateArtistButton() {

    }

    // EFFECTS: prompts user for an album and new genre, then updates that album to
    // have the new genre
    private void createUpdateGenreButton() {

    }

    // EFFECTS: prompts user for an album and new rating, then updates that album to
    // have the new rating
    private void createUpdateRatingButton() {

    }

    // EFFECTS: prompts user for an album and new review, then updates that album to
    // have the new artist
    private void createUpdateReviewButton() {

    }

}