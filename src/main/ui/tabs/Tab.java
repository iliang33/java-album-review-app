package ui.tabs;

import javax.swing.*;

import model.ReviewManager;

import java.awt.*;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public class Tab extends JPanel {

    private final ReviewManager manager;
    protected JPanel sidebar;
    protected static final int BUTTON_WIDTH = 200;
    protected static final int BUTTON_HEIGHT = 30;
    protected static final Dimension BUTTON_DIMENSION = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

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

    protected void createSidebar() {
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        add(sidebar, BorderLayout.EAST);

    }

    public ReviewManager getManager() {
        return this.manager;
    }

}
