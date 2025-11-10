package ui.tabs;

import javax.swing.JButton;

import model.ReviewManager;
import ui.ButtonNames;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the update tab on the navbar of the GUI where 
// updating existing album review info is done

public class UpdateTab extends Tab {

    // EFFECTS: creates an update tab displaying all albums reviews and a sidebar
    // containing buttons related to updating album review fields
    public UpdateTab(ReviewManager manager) {
        super(manager);
        createSidebar();
        addButtonsToSidebar();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons related to updating album review operations to the
    // sidebar
    private void addButtonsToSidebar() {
        createUpdateNameButton();
        createUpdateArtistButton();
        createUpdateGenreButton();
        createUpdateRatingButton();
        createUpdateReviewButton();

    }

    // EFFECTS: prompts user for an album and new name, then updates that album to
    // have the new name
    private void createUpdateNameButton() {
        JButton button = createButton(ButtonNames.UPDATE_NAME.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            
        });

        addToSidebar(button);

    }

    // EFFECTS: prompts user for an album and new artist, then updates that album to
    // have the new artist
    private void createUpdateArtistButton() {
        JButton button = createButton(ButtonNames.UPDATE_ARTIST.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            
        });

        addToSidebar(button);

    }

    // EFFECTS: prompts user for an album and new genre, then updates that album to
    // have the new genre
    private void createUpdateGenreButton() {
        JButton button = createButton(ButtonNames.UPDATE_GENRE.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            
        });

        addToSidebar(button);

    }

    // EFFECTS: prompts user for an album and new rating, then updates that album to
    // have the new rating
    private void createUpdateRatingButton() {
        JButton button = createButton(ButtonNames.UPDATE_RATING.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            
        });

        addToSidebar(button);

    }

    // EFFECTS: prompts user for an album and new review, then updates that album to
    // have the new artist
    private void createUpdateReviewButton() {
        JButton button = createButton(ButtonNames.UPDATE_REVIEW.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            
        });
        
        addToSidebar(button);

    }

}