package ui;

import model.Album;
import model.AlbumCategory;
import model.Song;

public class Main {
    public static void main(String[] args) {

        AlbumCategory testAlbumCategory = new AlbumCategory("Pop Albums");
        Album testAlbum = new Album("Melodrama", "Lorde", "Alt-Pop", 8.5, "");
        Album testAlbum2 = new Album("Speak Now", "Taylor Swift", "Pop Rock", 9.1, "");
        Album otherTestAlbum = new Album("After Laughter", "Paramore", "Pop Rock", 8.0, "");
        Album lastTestAlbum = new Album("21", "Adele", "Pop Soul", 8.3, "");

        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(testAlbum2);
        testAlbumCategory.addAlbum(otherTestAlbum);
        testAlbumCategory.addAlbum(lastTestAlbum);



        System.out.println(testAlbumCategory.albumListToString());

        // new AlbumReviewApp();
    }
}
