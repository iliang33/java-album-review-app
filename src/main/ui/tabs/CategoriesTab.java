package ui.tabs;

import model.ReviewManager;

// represents the categories tab on the navbar of the GUI where 
// category creation, removal. adding to, removing from, and name updating is done

public class CategoriesTab extends Tab {

    // EFFECTS: creates a categories tab displaying all categories and sidebar
    // containing buttons related to category operations
    public CategoriesTab(ReviewManager manager) {
        super(manager);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons related to category operations to the sidebar
    private void addButtonsToSidebar() {

    }

    // EFFECTS: displays all created categories
    private void showCreatedCategories() {

    }

    // EFFECTS: creates an add category button that when clicked, prompts the user
    // for
    // a name and creates a category with that name
    private void createCategoryButton() {

    }

    // EFFECTS: creates a remove category button that when clicked, prompts the user
    // for a category and removes it
    private void createRemoveCategoryButton() {

    }

    // EFFECTS: creates an add to category button that when clicked, prompts the
    // user for a category and album then adds the album to the category
    private void createAddToCategoryButton() {

    }

    // EFFECTS: creates a remove from category button that when clicked, prompts the
    // user for a category and album, then removes the album from the category
    private void createRemoveFromCategoryButton() {

    }

    // EFFECTS: creates an update name button that when clicked, prompts the user
    // for a category, and a name, then updates that category's name to the name
    private void createUpdateNameButton() {

    }

}
