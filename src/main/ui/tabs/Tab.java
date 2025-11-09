package ui.tabs;

import javax.swing.*;

import model.ReviewManager;

import java.awt.*;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents a tab that can have a sidebar and buttons
public class Tab extends JPanel {

    protected final ReviewManager manager;
    protected JPanel sidebar;
    protected static final int BUTTON_WIDTH = 500;
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

    // MODIFIES: this
    // EFFECTS: creates a new sidebar fixed to the right of the screen
    protected void createSidebar() {
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        add(sidebar, BorderLayout.EAST);
     

    }

    // EFFECTS: creates and returns a new button with the given name, and size
    protected JButton createButton(String buttonName, Dimension size) {
        JButton button = new JButton(buttonName);
        button.setMaximumSize(size);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        sidebar.add(button);

        return button;

    }

    protected String getUserInput(String prompt){
        String inputValue = JOptionPane.showInputDialog(prompt);
        return inputValue;
    }


    public ReviewManager getManager() {
        return this.manager;
    }

}
