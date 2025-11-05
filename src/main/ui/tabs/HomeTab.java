package ui.tabs;

import model.ReviewManager;

// represents the home tab on the navbar of the GUI
public class HomeTab extends Tab {

    // EFFECTS: creates a home tab with a welcome message and exit, load and save
    // buttons
    public HomeTab(ReviewManager manager) {
        super(manager);
    }

    // EFFECTS: creates the welcome message that is showned at the top of the home
    // tab
    private void createWelcomeMessage() {

    }

    // EFFECTS: creates a save button that when clicked, saves all created albums
    // and categories to file
    private void createSaveButton() {

    }

    // EFFECTS: creates a load button that when clicked, loads all created albums
    // and categories from file
    private void createLoadButton() {

    }

    // EFFECTS: creates an exit button that when clicked, quits the application
    private void createExitButton() {

    }

}
