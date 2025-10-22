package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Album;
import model.Song;

// referenced from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

@ExcludeFromJacocoGeneratedReport
public class JsonTest {

    protected void checkAlbum(String name, String artist, String genre, double rating, String review, Album album) {
        assertEquals(name, album.getName());
        assertEquals(artist, album.getArtist());
        assertEquals(genre, album.getGenre());
        assertEquals(rating, album.getRating());
        assertEquals(review, album.getReview());
    }

    protected void checkSong(String name, String artist, double rating, String review, Song song) {
        assertEquals(name, song.getName());
        assertEquals(artist, song.getArtist());
        assertEquals(rating, song.getRating());
        assertEquals(review, song.getReview());
    }
}