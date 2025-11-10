package ui;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public enum Prompts {
    ALBUM_NAME("Enter album name"),
    ARTIST("Enter artist name"),
    GENRE("Enter genre"),
    RATING("Enter rating"),
    REVIEW("Enter review"),
    FIRST_ALBUM_NAME("Enter name of first album (this one will have the merged tracklist)"),
    FIRST_ARTIST("Enter artist name of first album"),
    SECOND_ALBUM_NAME("Enter name of second album (this album will be deleted)"),
    SECOND_ARTIST("Enter artist name of second album"),
    SONG("Enter song name"),
    CONTINUE("Continue?"),
    SONG_NUMBER("Enter number of song in tracklist"),
    CATEGORY_NAME("Enter category name"),
    NEW_VALUE("Enter new value");
    

    private final String prompt;

    Prompts(String prompt) {
        this.prompt = prompt;
    }

    // EFFECTS: returns the needed prompt
    public String getValue() {
        return prompt;
    }
}
