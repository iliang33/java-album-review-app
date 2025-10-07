package ui;

import model.Album;
import model.Song;

public class Main {
    public static void main(String[] args) {

        Album testAlbum = new Album("Speak Now", "Taylor Swift", "Pop Rock", 9.1, "");
        Song testSong = new Song("Mine", "Taylor Swift", 3, 51, 9, "");
        Song otherTestSong = new Song("Sparks Fly", "Taylor Swift", 4, 21, 9.5, "");
        Song oneMoreTestSong = new Song("Dear John", "Taylor Swift", 6, 45, 9, "");

        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        System.out.println(testAlbum.trackListToString());

        // new AlbumReviewApp();
    }
}
