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
    SORT_RATING("Sort by rating"),
    ADD_CATEGORY("Add category"),
    REMOVE_CATEGORY("Remove catgory"),
    ADD_TO_CATEGORY("Add to category"),
    REMOVE_FROM_CATEGORY("Remove from category"),
    UPDATE_CATEGORY_NAME("Update category name"),
    UPDATE_NAME("Update album name"),
    UPDATE_ARTIST("Update artist"),
    UPDATE_GENRE("Update genre"),
    UPDATE_RATING("Update rating"),
    UPDATE_REVIEW("Update review");


    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    // EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
