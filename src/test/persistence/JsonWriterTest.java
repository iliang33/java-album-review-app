package persistence;

import org.junit.jupiter.api.Test;

import model.Album;
import model.AlbumCategory;
import model.ReviewManager;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// referenced from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Set of tests for JsonWriter
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\tinvalid::{file.json");
            writer.open();
            fail("Expected an IOException");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyReviewManager() {
        try {
            ReviewManager manager = new ReviewManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyReviewManager.json");
            writer.open();
            writer.writeReviewManager(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyReviewManager.json");
            manager = reader.readReviewManager();
            assertTrue(manager.getAlbumsList().isEmpty());
            assertTrue(manager.getAlbumCategoriesList().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNonEmptyManager() {
        try {
            ReviewManager manager = new ReviewManager();

            manager.addAlbum(new Album("SOUR", "Olivia Rodrigo", "Pop Rock", 8.5, "Fun"));

            AlbumCategory category1 = new AlbumCategory("My Other Top Albums");

            manager.addCategory(category1);

            manager.addToCategory(category1, "SOUR", "Olivia Rodrigo");

            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyManager.json");
            writer.open();
            writer.writeReviewManager(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyManager.json");
            manager = reader.readReviewManager();

            List<Album> albums = manager.getAlbumsList();
            List<AlbumCategory> categories = manager.getAlbumCategoriesList();

            checkAlbum("SOUR", "Olivia Rodrigo", "Pop Rock", 8.5, "Fun", albums.get(0));

            assertTrue(albums.get(0).getTracklist().isEmpty());

            assertEquals("My Other Top Albums", categories.get(0).getName());

            checkAlbum("SOUR", "Olivia Rodrigo", "Pop Rock", 8.5, "Fun",
                    categories.get(0).getAlbumList().get(0));
            assertTrue(categories.get(0).getAlbumList().get(0).getTracklist().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNonEmptyTracklist() {
        try {
            ReviewManager manager = new ReviewManager();
            Album album = new Album("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing");

            manager.addAlbum(album);
            manager.addToAlbumTracklist(album, "Dark Paradise", "Lana Del Rey", 9.5, "Wow");
            manager.addToAlbumTracklist(album, "Off To The Races", "Lana Del Rey", 9.5, "Amazing");

            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyTracklist.json");
            writer.open();
            writer.writeReviewManager(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyTracklist.json");
            manager = reader.readReviewManager();

            List<Album> albums = manager.getAlbumsList();
            List<AlbumCategory> categories = manager.getAlbumCategoriesList();

            checkAlbum("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing", albums.get(0));

            checkSong("Dark Paradise", "Lana Del Rey", 9.5, "Wow", albums.get(0).getTracklist().get(0));
            checkSong("Off To The Races", "Lana Del Rey", 9.5, "Amazing", albums.get(0).getTracklist().get(1));
            assertEquals(albums.get(0).getTracklist().size(), 2);
            assertTrue(categories.isEmpty());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
