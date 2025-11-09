package ui.tabs;

import javax.swing.JButton;

import model.ReviewManager;
import ui.ButtonNames;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the categories tab on the navbar of the GUI where 
// category creation, removal. adding to, removing from, and name updating is done

public class CategoriesTab extends Tab {

    // EFFECTS: creates a categories tab displaying all categories and sidebar
    // containing buttons related to category operations
    public CategoriesTab(ReviewManager manager) {
        super(manager);
        createSidebar();
        addButtonsToSidebar();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons related to category operations to the sidebar
    private void addButtonsToSidebar() {
        createCategoryButton();
        createRemoveCategoryButton();
        createAddToCategoryButton();
        createRemoveFromCategoryButton();
        createUpdateNameButton();
    }

    // EFFECTS: creates an add category button that when clicked, prompts the user
    // for
    // a name and creates a category with that name
    private void createCategoryButton() {
        JButton button = createButton(ButtonNames.ADD_CATEGORY.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates a remove category button that when clicked, prompts the user
    // for a category and removes it
    private void createRemoveCategoryButton() {
        JButton button = createButton(ButtonNames.REMOVE_CATEGORY.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates an add to category button that when clicked, prompts the
    // user for a category and album then adds the album to the category
    private void createAddToCategoryButton() {
        JButton button = createButton(ButtonNames.ADD_TO_CATEGORY.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates a remove from category button that when clicked, prompts the
    // user for a category and album, then removes the album from the category
    private void createRemoveFromCategoryButton() {
        JButton button = createButton(ButtonNames.REMOVE_FROM_CATEGORY.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

    // EFFECTS: creates an update name button that when clicked, prompts the user
    // for a category, and a name, then updates that category's name to the name
    private void createUpdateNameButton() {
        JButton button = createButton(ButtonNames.UPDATE_NAME.getValue(), BUTTON_DIMENSION);
        addToSidebar(button);

    }

}
