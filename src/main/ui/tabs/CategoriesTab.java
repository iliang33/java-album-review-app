package ui.tabs;

import javax.swing.JButton;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import exceptions.PopUpClosedOrCancelledException;
import model.Album;
import model.AlbumCategory;
import ui.ButtonNames;
import ui.ErrorMessages;
import ui.Prompts;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the categories tab on the navbar of the GUI where 
// category related operations are done
@ExcludeFromJacocoGeneratedReport
public class CategoriesTab extends Tab {

    // EFFECTS: creates a categories tab with a sidebar
    // containing buttons related to category operations
    public CategoriesTab() {
        super();
        createSidebar();
        addButtonsToSidebar();
        setVisible(true);
    }

    // EFFECTS: adds all buttons related to category operations to the sidebar
    private void addButtonsToSidebar() {
        createCategoryButton();
        createRemoveCategoryButton();
        createAddToCategoryButton();
        createRemoveFromCategoryButton();
        createUpdateNameButton();
    }

    // EFFECTS: creates an add category button that when clicked, prompts the user
    // for a name and creates a category with that name
    private void createCategoryButton() {
        JButton button = createButton(ButtonNames.ADD_CATEGORY.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {

            try {
                String name = getUserInput(Prompts.CATEGORY_NAME.getValue());

                if (manager.getWantedCategory(name) == null) {
                    manager.addCategory(new AlbumCategory(name));

                } else {
                    showErrorMessage(this, ErrorMessages.DUPLICATE_CATEGORY.getValue());
                }
            } catch (PopUpClosedOrCancelledException except) {
                // do nothing

            }

        });

        addToSidebar(button);

    }

    // EFFECTS: creates a remove category button that when clicked, prompts the user
    // for a category (referenced by name) and removes it
    private void createRemoveCategoryButton() {
        JButton button = createButton(ButtonNames.REMOVE_CATEGORY.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {
            try {
                String name = getUserInput(Prompts.CATEGORY_NAME.getValue());

                AlbumCategory categoryToRemove = manager.getWantedCategory(name);

                if (categoryToRemove != null) {
                    manager.removeCategory(categoryToRemove);

                } else {
                    showErrorMessage(this, ErrorMessages.NO_CATEGORY.getValue());
                }
            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing

            }

        });

        addToSidebar(button);

    }

    // EFFECTS: creates an add to category button that when clicked, prompts the
    // user for a category (referenced by name) and album (referenced by name and
    // artist), then adds the album to the category
    private void createAddToCategoryButton() {
        JButton button = createButton(ButtonNames.ADD_TO_CATEGORY.getValue(), BUTTON_DIMENSION);
        button.addActionListener(e -> {
            try {
                String name = getUserInput(Prompts.CATEGORY_NAME.getValue());
                String albumName = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artistName = getUserInput(Prompts.ARTIST.getValue());

                AlbumCategory categoryToAddto = manager.getWantedCategory(name);

                if (categoryToAddto != null && manager.getWantedAlbum(albumName, artistName) != null) {
                    if (manager.getWantedAlbumInWantedCategory(albumName, artistName, categoryToAddto) == null) {
                        manager.addToCategory(categoryToAddto, albumName, artistName);
                    } else {
                        showErrorMessage(this, ErrorMessages.ALBUM_IN_CATEGORY.getValue());
                    }

                } else {
                    showErrorMessage(this,
                            ErrorMessages.NO_CATEGORY.getValue() + " and/or " + ErrorMessages.NO_ALBUM.getValue());
                }

            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing
            }

        });

        addToSidebar(button);

    }

    // EFFECTS: creates a remove from category button that when clicked, prompts the
    // user for a category (referenced by name) and album (referenced by name and
    // artist), then removes the album from the category
    private void createRemoveFromCategoryButton() {
        JButton button = createButton(ButtonNames.REMOVE_FROM_CATEGORY.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {

            try {

                String name = getUserInput(Prompts.CATEGORY_NAME.getValue());
                String albumName = getUserInput(Prompts.ALBUM_NAME.getValue());
                String artistName = getUserInput(Prompts.ARTIST.getValue());

                AlbumCategory categoryToRemoveFrom = manager.getWantedCategory(name);
                Album wantedAlbum = manager.getWantedAlbum(albumName, artistName);

                if (categoryToRemoveFrom != null) {
                    if (wantedAlbum != null) {
                        manager.removeFromCategory(categoryToRemoveFrom, albumName, artistName);
                    } else {
                        showErrorMessage(this, ErrorMessages.NO_ALBUM.getValue());
                    }
                } else {
                    showErrorMessage(this, ErrorMessages.NO_CATEGORY.getValue());
                }

            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing

            }

        });

        addToSidebar(button);

    }

    // EFFECTS: creates an update name button that when clicked, prompts the user
    // for a category (referenced by name), and a new name, then updates that
    // category's name to the new name
    private void createUpdateNameButton() {
        JButton button = createButton(ButtonNames.UPDATE_NAME.getValue(), BUTTON_DIMENSION);

        button.addActionListener(e -> {

            try {

                String oldName = getUserInput(Prompts.CATEGORY_NAME.getValue());
                String newName = getUserInput(Prompts.NEW_CATEGORY_NAME.getValue());

                if (manager.getWantedCategory(oldName) != null) {
                    for (int i = 0; i < manager.getAlbumCategoriesList().size(); i++) {
                        AlbumCategory currentCategory = manager.getAlbumCategoriesList().get(i);
                        if (currentCategory.getName().equals(oldName)) {
                            currentCategory.setName(newName);
                            break;
                        }
                    }
                } else {
                    showErrorMessage(this, ErrorMessages.NO_CATEGORY.getValue());
                }
            } catch (PopUpClosedOrCancelledException exception) {
                // do nothing

            }

        });

        addToSidebar(button);

    }

}
