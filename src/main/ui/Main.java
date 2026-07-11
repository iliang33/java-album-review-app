package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new AlbumReviewGUI();
            // new AlbumReviewApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");

        }

    }
}
