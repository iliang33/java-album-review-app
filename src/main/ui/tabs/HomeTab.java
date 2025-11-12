package ui.tabs;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Album;
import model.AlbumCategory;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.AlbumReviewGUI;
import ui.ButtonNames;
import ui.ErrorMessages;

// referenced from SmartHomeUI, JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// represents the home tab on the navbar of the GUI where saving, loading, and exiting is done
public class HomeTab extends Tab {
    private JLabel welcomeMsg;
    private static final String WELCOME = "Welcome to the Album Review App";
    private AlbumReviewGUI gui;

    // EFFECTS: creates a home tab with a welcome message and exit, load and save
    // buttons
    public HomeTab(AlbumReviewGUI gui) {
        super();
        this.gui = gui;

        setLayout(new GridLayout(4, 1));

        createWelcomeMessage();
        createHomeButtons();

    }

    // EFFECTS: creates the welcome message that is showned at the top of the home
    // tab
    private void createWelcomeMessage() {
        welcomeMsg = new JLabel(WELCOME, JLabel.CENTER);
        welcomeMsg.setSize(WIDTH, HEIGHT / 3);
        welcomeMsg.setFont(new Font("Times New Roman", Font.BOLD, 25));
        this.add(welcomeMsg);

    }

    // EFFECTS: creates save, load, and exit buttons
    private void createHomeButtons() {
        createSaveButton();
        createLoadButton();
        createExitButton();

    }

    // EFFECTS: creates save button that saves to file when pressed
    private void createSaveButton() {
        JButton save = createButton(ButtonNames.SAVE.getValue(), BUTTON_DIMENSION);
        JsonWriter writer = gui.getJsonWriter();

        save.addActionListener(e -> {

            try {
                writer.open();
                writer.writeReviewManager(manager);
                writer.close();

            } catch (FileNotFoundException exception) {
                showErrorMessage(this, ErrorMessages.NO_FILE.getValue());
            }

        });

        JPanel buttonRow = formatButtonRow(save);
        add(buttonRow);

    }

    // EFFECTS: creates load button that loads from file when pressed
    private void createLoadButton() {

        JButton load = createButton(ButtonNames.LOAD.getValue(), BUTTON_DIMENSION);

        JsonReader reader = gui.getJsonReader();

        load.addActionListener(e -> {

            try {
                manager = reader.readReviewManager();
                recreateSharedReferences();
            } catch (IOException exception) {
                showErrorMessage(this, ErrorMessages.NO_FILE.getValue());
            }

        });

        JPanel buttonRow = formatButtonRow(load);
        add(buttonRow);

    }

    // EFFECTS: creates exit button that closes the gui when pressed
    private void createExitButton() {
        JButton exit = createButton(ButtonNames.EXIT.getValue(), BUTTON_DIMENSION);

        exit.addActionListener(e -> {
            gui.dispose();

        });

        JPanel buttonRow = formatButtonRow(exit);
        add(buttonRow);

    }

    // EFFECTS: recreates any shared references between albums and album categories
    // that were erased when serializied to JSON
    public void recreateSharedReferences() {
        for (Album album : manager.getAlbumsList()) {
            if (manager.albumIsInAnyCategory(album)) {
                for (AlbumCategory category : manager.getAlbumCategoriesList()) {
                    List<Album> currentCategoryAlbumList = category.getAlbumList();
                    for (int i = 0; i < currentCategoryAlbumList.size(); i++) {
                        Album currentAlbum = currentCategoryAlbumList.get(i);
                        if (currentAlbum.equals(album)) {
                            currentCategoryAlbumList.remove(currentAlbum);
                            currentCategoryAlbumList.add(i, album);

                        }

                    }

                }

            }
        }

    }

}
