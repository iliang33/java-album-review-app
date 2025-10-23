package persistence;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Album;
import model.AlbumCategory;
import model.Song;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// referenced from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentAlbumCategoryFile() {
        JsonReader reader = new JsonReader("./data/nonExistentAlbumCategoryFile.json");
        try {
            reader.readAlbumCategory();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderNonExistentAlbumFile() {
        JsonReader reader = new JsonReader("./data/nonExistentAlbumFile.json");
        try {
            reader.readAlbum();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyAlbumCategory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAlbumCategory.json");
        try {
            AlbumCategory category = reader.readAlbumCategory();
            assertEquals("Pop albums", category.getName());
            assertTrue(category.getAlbumList().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderAlbumEmptyTracklist() {
        JsonReader reader = new JsonReader("./data/testReaderAlbumEmptyTracklist.json");
        try {
            Album album = reader.readAlbum();
            assertEquals("Silence Between Songs", album.getName());
            assertEquals("Madison Beer", album.getArtist());
            assertEquals("Alt-Pop", album.getGenre());
            assertEquals(7.5, album.getRating());
            assertEquals("Good", album.getReview());
            assertTrue(album.getTracklist().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderAlbumCategoryTwoAlbums() {
        JsonReader reader = new JsonReader("./data/testReaderAlbumCategoryTwoAlbums.json");
        try {
            AlbumCategory category = reader.readAlbumCategory();
            assertEquals("My Top Albums", category.getName());
            List<Album> albums = category.getAlbumList();
            assertEquals(2, albums.size());
            checkAlbum("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing", albums.get(0));
            checkAlbum("Speak Now", "Taylor Swift", "Pop Rock", 9.0, "Her best", albums.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderAlbumNonEmptyTracklist() {
        JsonReader reader = new JsonReader("./data/testReaderAlbumNonEmptyTracklist.json");
        try {
            Album album = reader.readAlbum();
            checkAlbum("Silence Between Songs", "Madison Beer", "Alt-Pop", 7.5, "Good", album);
            List<Song> tracklist = album.getTracklist();
            assertEquals(2, tracklist.size());
            checkSong("Spinnin", "Madison Beer", 8, "Good opener", tracklist.get(0));
            checkSong("Sweet Relief", "Madison Beer", 8, "Fun", tracklist.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}