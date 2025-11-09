package ui;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public enum Prompts {
    ALBUM_NAME("Enter album name"),
    ARTIST("Enter artist name");
    

    private final String prompt;

    Prompts(String prompt) {
        this.prompt = prompt;
    }

    // EFFECTS: returns the needed prompt
    public String getValue() {
        return prompt;
    }
}
