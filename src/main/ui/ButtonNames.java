package ui;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public enum ButtonNames {
    SAVE("Save to file"),
    LOAD("Load from file"),
    EXIT("Exit app");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    // EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
