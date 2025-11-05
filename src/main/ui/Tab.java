package ui;

import javax.swing.*;

import model.ReviewManager;

import java.awt.*;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public class Tab extends JPanel {

    private final ReviewManager manager;

    public Tab(ReviewManager manager) {
        this.manager = manager;
    }

    // EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton button) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);

        return panel;
    }

    // EFFECTS: returns the ReviewManager for this tab
    public ReviewManager getManager() {
        return this.manager;
    }

}
