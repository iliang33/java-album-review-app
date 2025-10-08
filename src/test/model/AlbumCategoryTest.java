package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlbumCategoryTest {
    AlbumCategory testAlbumCategory;
    Album testAlbum;
    Album otherTestAlbum;
    Album lastTestAlbum;

    @BeforeEach
    void runBefore() {
        testAlbumCategory = new AlbumCategory("Pop Albums");
        testAlbum = new Album("Melodrama", "Lorde", "Alt-Pop", 8.5, "");
        otherTestAlbum = new Album("After Laughter", "Paramore", "Pop Rock", 8.0, "");
        lastTestAlbum = new Album("21", "Adele", "Pop Soul", 8.3, "");

    }

    @Test
    void testConstructor() {
        assertEquals(testAlbumCategory.getName(), "Pop Albums");
        assertTrue(testAlbumCategory.getAlbumList().isEmpty());
    }

    @Test
    void testAddAlbum() {
        testAlbumCategory.addAlbum(testAlbum);

        assertEquals(testAlbumCategory.getAlbumList().get(0), testAlbum);
        assertEquals(testAlbumCategory.getAlbumList().size(), 1);

        testAlbumCategory.addAlbum(otherTestAlbum);

        assertEquals(testAlbumCategory.getAlbumList().get(1), otherTestAlbum);
        assertEquals(testAlbumCategory.getAlbumList().size(), 2);

    }

    @Test
    void testRemoveAlbum() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);
        testAlbumCategory.addAlbum(lastTestAlbum);

        testAlbumCategory.removeAlbum("Melodrama", "Lorde");
        assertFalse(testAlbumCategory.getAlbumList().contains(testAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 2);

        testAlbumCategory.removeAlbum("After Laughter", "Paramore");
        assertFalse(testAlbumCategory.getAlbumList().contains(otherTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 1);

        testAlbumCategory.removeAlbum("21", "Adele");
        assertFalse(testAlbumCategory.getAlbumList().contains(lastTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 0);

    }

    @Test
    void testRemoveAlbumLastFirst() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);
        testAlbumCategory.addAlbum(lastTestAlbum);

        testAlbumCategory.removeAlbum("21", "Adele");
        assertFalse(testAlbumCategory.getAlbumList().contains(lastTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 2);

        testAlbumCategory.removeAlbum("Melodrama", "Lorde");
        assertFalse(testAlbumCategory.getAlbumList().contains(testAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 1);

        testAlbumCategory.removeAlbum("After Laughter", "Paramore");
        assertFalse(testAlbumCategory.getAlbumList().contains(otherTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 0);

    }

    @Test
    void testRemoveAlbumMiddleFirst() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);
        testAlbumCategory.addAlbum(lastTestAlbum);

        testAlbumCategory.removeAlbum("After Laughter", "Paramore");
        assertFalse(testAlbumCategory.getAlbumList().contains(otherTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 2);

        testAlbumCategory.removeAlbum("Melodrama", "Lorde");
        assertFalse(testAlbumCategory.getAlbumList().contains(testAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 1);

        testAlbumCategory.removeAlbum("21", "Adele");
        assertFalse(testAlbumCategory.getAlbumList().contains(lastTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 0);

    }

    @Test
    void testCreateAlbum() {
        Album newAlbum = new Album("Dangerous Woman", "Ariana Grande", "Dance-Pop", 7.9, "");
        Album anotherAlbum = new Album("The Secret of Us", "Gracie Abrams", "Pop", 8.1, "");

        assertEquals(newAlbum.getName(), "Dangerous Woman");
        assertEquals(newAlbum.getArtist(), "Ariana Grande");
        assertEquals(newAlbum.getGenre(), "Dance-Pop");
        assertTrue(newAlbum.getTracklist().isEmpty());
        assertEquals(newAlbum.getNumberOfSongs(), 0);
        assertEquals(newAlbum.getRating(), 7.9);
        assertEquals(newAlbum.getReview(), "");

        assertEquals(anotherAlbum.getName(), "The Secret of Us");
        assertEquals(anotherAlbum.getArtist(), "Gracie Abrams");
        assertEquals(anotherAlbum.getGenre(), "Pop");
        assertTrue(anotherAlbum.getTracklist().isEmpty());
        assertEquals(anotherAlbum.getNumberOfSongs(), 0);
        assertEquals(anotherAlbum.getRating(), 8.1);
        assertEquals(anotherAlbum.getReview(), "");

    }

    @Test
    void testAlbumListToStringNoAlbums() {
        assertEquals(testAlbumCategory.albumListToString(), "");
    }

    @Test
    void testAlbumListToStringOneAlbum() {
        testAlbumCategory.addAlbum(testAlbum);
        assertEquals(testAlbumCategory.albumListToString(), "1. Melodrama by Lorde");
    }

    @Test
    void testAlbumListToStringThreeAlbums() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);
        testAlbumCategory.addAlbum(lastTestAlbum);

        assertEquals(testAlbumCategory.albumListToString(),
                "1. Melodrama by Lorde\n2. After Laughter by Paramore\n3. 21 by Adele");
    }

}
