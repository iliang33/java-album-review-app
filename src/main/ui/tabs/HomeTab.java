package ui.tabs;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ReviewManager;
import ui.ButtonNames;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the home tab on the navbar of the GUI
public class HomeTab extends Tab {
    private JLabel welcomeMsg;
    private static final String WELCOME = "Welcome to the Album Review App";

    // EFFECTS: creates a home tab with a welcome message and exit, load and save
    // buttons
    public HomeTab(ReviewManager manager) {
        super(manager);

        setLayout(new GridLayout(3, 1));

        createWelcomeMessage();
        createSaveLoadButtons();
        createExitButton();
    }

    // EFFECTS: creates the welcome message that is showned at the top of the home
    // tab
    private void createWelcomeMessage() {
        welcomeMsg = new JLabel(WELCOME, JLabel.CENTER);
        welcomeMsg.setSize(WIDTH, HEIGHT / 3);
        welcomeMsg.setFont(new Font("Times New Roman", Font.BOLD, 25));
        this.add(welcomeMsg);

    }

    // EFFECTS: creates save and load buttons
    private void createSaveLoadButtons() {
        JButton save = new JButton(ButtonNames.SAVE.getValue());
        JButton load = new JButton(ButtonNames.LOAD.getValue());

        JPanel buttonRow = formatButtonRow(save);
        buttonRow.add(load);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        this.add(buttonRow);

    }

    // EFFECTS: creates exit buttons
    private void createExitButton() {
        JButton exit = new JButton(ButtonNames.EXIT.getValue());

        JPanel buttonRow = formatButtonRow(exit);
        buttonRow.setSize(WIDTH, HEIGHT / 6);
        this.add(buttonRow);

    }

}
