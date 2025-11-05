package ui;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public enum ButtonNames {
    SAVE("Save to file"),
    LOAD("Load from file"),
    EXIT("Exit app"),
    ADD_ALBUM("Add album review"),
    REMOVE_ALBUM("Remove album review"),
    MERGE("Merge album tracklists"),
    ADD_TO_TRACKLIST("Add to tracklist"),
    REMOVE_FROM_TRACKLIST("Remove from tracklist"),
    SORT_ARTIST("Sort by artist"),
    SORT_NAME("Sort by name"),
    SORT_RATING("Sort by rating");

    
    

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    // EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
