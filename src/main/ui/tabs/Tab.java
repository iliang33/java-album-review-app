package ui.tabs;

import javax.swing.*;

import model.ReviewManager;

import java.awt.*;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public class Tab extends JPanel {

    private final ReviewManager manager;
    protected static final int BUTTON_WIDTH = 200;
    protected static final int BUTTON_HEIGHT = 30;

    public Tab(ReviewManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());
    }

    // EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton button) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);

        return panel;
    }

    public ReviewManager getManager() {
        return this.manager;
    }

}
