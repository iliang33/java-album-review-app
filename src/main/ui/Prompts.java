package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

@ExcludeFromJacocoGeneratedReport

public enum Prompts {
    ALBUM_NAME("Enter album name"),
    ARTIST("Enter artist name"),
    GENRE("Enter genre"),
    RATING("Enter rating (1 decimal place and between 0.0 and 10.0)"),
    REVIEW("Enter review"),
    FIRST_ALBUM_NAME("Enter name of first album (this one will have the merged tracklist)"),
    FIRST_ARTIST("Enter artist name of first album"),
    SECOND_ALBUM_NAME("Enter name of second album (this album will be deleted)"),
    SECOND_ARTIST("Enter artist name of second album"),
    SONG("Enter song name"),
    CONTINUE("Continue?"),
    SONG_NUMBER("Enter number of song in tracklist"),
    CATEGORY_NAME("Enter category name"),
    NEW_CATEGORY_NAME("Enter new name for the category"),
    NEW_ALBUM_NAME("Enter new album name"),
    NEW_ARTIST("Enter new artist"),
    NEW_GENRE("Enter new genre"),
    NEW_RATING("Enter new rating (1 decimal place and between 0.0 and 10.0)"),
    NEW_REVIEW("Enter new review");

    private final String prompt;

    Prompts(String prompt) {
        this.prompt = prompt;
    }

    // EFFECTS: returns prompt
    public String getValue() {
        return prompt;
    }
}
