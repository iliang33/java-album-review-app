package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

@ExcludeFromJacocoGeneratedReport

public enum ErrorMessages {
    DUPLICATE_ALBUM("Album already exists"),
    NOT_A_NUM("Given input was not a number"),
    NOT_IN_RANGE("Rating not in range of 0.0 to 10.0"),
    NO_ALBUM("Album not found"),
    DUPLICATE_SONG("Song already in tracklist"),
    NOT_A_SONG_NUMBER("Not a valid song number"),
    DUPLICATE_CATEGORY("Category already exists"),
    NO_CATEGORY("Category not found"),
    ALBUM_IN_CATEGORY("Album already in the category"),
    INVALID("Invalid input"),
    NO_FILE("Error: failed to read/write to/from file");

    private final String errMsg;

    ErrorMessages(String errMsg) {
        this.errMsg = errMsg;
    }

    // EFFECTS: returns error message
    public String getValue() {
        return errMsg;
    }
}
