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
class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentReviewManagerFile() {
        JsonReader reader = new JsonReader("./data/nonExistentReviewManagerFile.json");
        try {
            reader.readReviewManager();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyReviewManager() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyReviewManager.json");
        try {
            ReviewManager manager = reader.readReviewManager();
            assertTrue(manager.getAlbumsList().isEmpty());
            assertTrue(manager.getAlbumCategoriesList().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderManagerTwoAlbumTwoCategories() {
        JsonReader reader = new JsonReader("./data/testReaderManagerTwoAlbumsTwoCategories.json");
        try {
            ReviewManager manager = reader.readReviewManager();

            List<Album> albums = manager.getAlbumsList();
            List<AlbumCategory> categories = manager.getAlbumCategoriesList();

            checkAlbum("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing", albums.get(0));
            checkAlbum("Speak Now", "Taylor Swift", "Pop Rock", 9.0, "Her best", albums.get(1));

            assertEquals("My Top Albums", categories.get(0).getName());
            assertEquals("My Other Top Albums", categories.get(1).getName());

            checkAlbum("Born to Die", "Lana Del Rey", "Alt-Pop", 9.1, "Amazing",
                    categories.get(0).getAlbumList().get(0));
            checkAlbum("Speak Now", "Taylor Swift", "Pop Rock", 9.0, "Her best",
                    categories.get(0).getAlbumList().get(1));

            checkAlbum("GUTS", "Olivia Rodrigo", "Pop Rock", 8.6, "Fun",
                    categories.get(1).getAlbumList().get(0));

            checkAlbum("SOUR", "Olivia Rodrigo", "Pop Rock", 8.5, "Fun",
                    categories.get(1).getAlbumList().get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}