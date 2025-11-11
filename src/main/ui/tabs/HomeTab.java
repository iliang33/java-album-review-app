package ui.tabs;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ReviewManager;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.AlbumReviewGUI;
import ui.ButtonNames;

// referenced from SmartHomeUI, JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// represents the home tab on the navbar of the GUI
public class HomeTab extends Tab {
    private JLabel welcomeMsg;
    private static final String WELCOME = "Welcome to the Album Review App";
    private AlbumReviewGUI gui;

    // EFFECTS: creates a home tab with a welcome message and exit, load and save
    // buttons
    public HomeTab(ReviewManager manager, AlbumReviewGUI gui) {
        super(manager);
        this.gui = gui;

        setLayout(new GridLayout(3, 1));

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
        JButton save = createButton(ButtonNames.SAVE.getValue(), BUTTON_DIMENSION);
        JButton load = createButton(ButtonNames.LOAD.getValue(), BUTTON_DIMENSION);
        JButton exit = createButton(ButtonNames.EXIT.getValue(), BUTTON_DIMENSION);

        JsonWriter writer = gui.getJsonWriter();
        JsonReader reader = gui.getJsonReader();
        String saveFile = gui.getJsonSaveFile();

        save.addActionListener(e -> {

            try {
                writer.open();
                writer.writeReviewManager(manager);
                writer.close();
                
            } catch (FileNotFoundException exception) {
                System.out.println("Error: failed to write to file: " + saveFile);
            }

        });

        load.addActionListener(e -> {

            try {
                manager = reader.readReviewManager();
            } catch (IOException exception) {
                System.out.println("Error: failed to read from file: " + saveFile);
            }

        });

        exit.addActionListener(e -> {
            gui.dispose();

        });

        JPanel buttonRow1 = formatButtonRow(save);
        buttonRow1.add(load);
        buttonRow1.setSize(WIDTH, HEIGHT / 6);
        add(buttonRow1);

        JPanel buttonRow2 = formatButtonRow(exit);
        buttonRow2.setSize(WIDTH, HEIGHT / 6);
        add(buttonRow2);

    }

}
