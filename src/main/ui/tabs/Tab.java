package ui.tabs;

import javax.swing.*;

import exceptions.PopUpClosedOrCancelledException;

import model.ReviewManager;

import java.awt.*;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents a tab that can have a sidebar and buttons
public class Tab extends JPanel {
    protected static final int BUTTON_WIDTH = 500;
    protected static final int BUTTON_HEIGHT = 30;
    protected static final Dimension BUTTON_DIMENSION = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
    
    

    protected static ReviewManager manager;
    protected JPanel sidebar;

    public Tab(ReviewManager manager) {
        Tab.manager = manager;
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
        button.setOpaque(false);

        return button;

    }

    // EFFECTS: adds given button to the sidebar
    protected void addToSidebar(JButton button) {
        sidebar.add(button);
    }

    protected String getUserInput(String prompt) throws PopUpClosedOrCancelledException {
        String inputValue = JOptionPane.showInputDialog(prompt);
        if (inputValue == null) {
            throw new PopUpClosedOrCancelledException();

        }
        return inputValue;
    }

    protected int getUserConfirmation(Component component, String prompt) {
        int confirmation = JOptionPane.showConfirmDialog(component, prompt);
        return confirmation;
    }

    protected void showErrorMessage(Component parentComponent, String msg) {
        JOptionPane.showMessageDialog(parentComponent, msg, "Error", JOptionPane.ERROR_MESSAGE);

    }

}
