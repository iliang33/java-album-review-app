package persistence;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Album;
import model.AlbumCategory;
import model.ReviewManager;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// referenced from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\tinvalid::{file.json");
            writer.open();
            fail("Expected an IOException");
        } catch (IOException e) {
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

            manager.addAlbum(new Album("Speak Now", "Taylor Swift", "Pop Rock", 9.0, "Her best"));
            manager.addAlbum(new Album("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing"));
            manager.addAlbum(new Album("GUTS", "Olivia Rodrigo", "Pop Rock", 8.6, "Fun"));
            manager.addAlbum(new Album("SOUR", "Olivia Rodrigo", "Pop Rock", 8.5, "Fun"));

            AlbumCategory category1 = new AlbumCategory("My Other Top Albums");
            AlbumCategory category2 = new AlbumCategory("My Top Albums");
            manager.addCategory(category1);
            manager.addCategory(category2);

            manager.addToCategory(category1, "GUTS", "Olivia Rodrigo");
            manager.addToCategory(category1, "SOUR", "Olivia Rodrigo");

            manager.addToCategory(category2, "Born to Die", "Lana Del Rey");
            manager.addToCategory(category2, "Speak Now", "Taylor Swift");

            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyManager.json");
            writer.open();
            writer.writeReviewManager(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyManager.json");
            manager = reader.readReviewManager();

            List<Album> albums = manager.getAlbumsList();
            List<AlbumCategory> categories = manager.getAlbumCategoriesList();

            checkAlbum("Speak Now", "Taylor Swift", "Pop Rock", 9.0, "Her best", albums.get(0));
            checkAlbum("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing", albums.get(1));
            checkAlbum("GUTS", "Olivia Rodrigo", "Pop Rock", 8.6, "Fun", albums.get(2));
            checkAlbum("SOUR", "Olivia Rodrigo", "Pop Rock", 8.5, "Fun", albums.get(3));

            assertTrue(albums.get(0).getTracklist().isEmpty());
            assertTrue(albums.get(1).getTracklist().isEmpty());
            assertTrue(albums.get(2).getTracklist().isEmpty());
            assertTrue(albums.get(3).getTracklist().isEmpty());

            assertEquals("My Other Top Albums", categories.get(0).getName());
            assertEquals("My Top Albums", categories.get(1).getName());

            checkAlbum("GUTS", "Olivia Rodrigo", "Pop Rock", 8.6, "Fun",
                    categories.get(0).getAlbumList().get(0));
            assertTrue(categories.get(0).getAlbumList().get(0).getTracklist().isEmpty());

            checkAlbum("SOUR", "Olivia Rodrigo", "Pop Rock", 8.5, "Fun",
                    categories.get(0).getAlbumList().get(1));
            assertTrue(categories.get(0).getAlbumList().get(1).getTracklist().isEmpty());

            checkAlbum("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing",
                    categories.get(1).getAlbumList().get(0));
            assertTrue(categories.get(1).getAlbumList().get(0).getTracklist().isEmpty());

            checkAlbum("Speak Now", "Taylor Swift", "Pop Rock", 9.0, "Her best",
                    categories.get(1).getAlbumList().get(1));
            assertTrue(categories.get(1).getAlbumList().get(1).getTracklist().isEmpty());
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

            AlbumCategory category = new AlbumCategory("My Top Albums");
            manager.addCategory(category);

            manager.addToCategory(category, "Born to Die", "Lana Del Rey");

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

            assertEquals("My Top Albums", categories.get(0).getName());

            checkAlbum("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing",
                    categories.get(0).getAlbumList().get(0));
            assertEquals(categories.get(0).getAlbumList().get(0).getTracklist().size(), 2);
            checkSong("Dark Paradise", "Lana Del Rey", 9.5, "Wow",
                    categories.get(0).getAlbumList().get(0).getTracklist().get(0));
            checkSong("Off To The Races", "Lana Del Rey", 9.5, "Amazing",
                    categories.get(0).getAlbumList().get(0).getTracklist().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
