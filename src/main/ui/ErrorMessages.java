package ui;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public enum ErrorMessages {
    DUPLICATE_ALBUM("Album already exists"),
    NOT_A_NUM("Given input was not a number"),
    NOT_IN_RANGE("Rating not in range of 0.0 to 10.0"),
    NO_ALBUM("Album not found"),
    ALBUM_IN_CATEGORY("Error: neither of the albums should be in a category"),
    DUPLICATE_SONG("Song already in tracklist"),
    NOT_A_SONG_NUMBER("Not a valid song number");
    

    private final String errMsg;

    ErrorMessages(String errMsg) {
        this.errMsg = errMsg;
    }

    // EFFECTS: returns the needed error message
    public String getValue() {
        return errMsg;
    }
}
