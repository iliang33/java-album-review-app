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
    void testRemoveAlbumNotInCategory() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);
        testAlbumCategory.addAlbum(lastTestAlbum);

        testAlbumCategory.removeAlbum("Folklore", "Taylor Swift");

        assertTrue(testAlbumCategory.getAlbumList().contains(otherTestAlbum));
        assertTrue(testAlbumCategory.getAlbumList().contains(testAlbum));
        assertTrue(testAlbumCategory.getAlbumList().contains(lastTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 3);

    }

    @Test
    void testRemoveAlbumWhereOneOfGivenNameAndArtistIsInTheCategory() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);
        testAlbumCategory.addAlbum(lastTestAlbum);

        testAlbumCategory.removeAlbum("Melodrama", "Taylor Swift");

        assertTrue(testAlbumCategory.getAlbumList().contains(otherTestAlbum));
        assertTrue(testAlbumCategory.getAlbumList().contains(testAlbum));
        assertTrue(testAlbumCategory.getAlbumList().contains(lastTestAlbum));
        assertEquals(testAlbumCategory.getAlbumList().size(), 3);

    }


    @Test
    void testSetName() {
        testAlbumCategory.setName("Pop Albums");
        assertEquals(testAlbumCategory.getName(), "Pop Albums");

        testAlbumCategory.setName("Rap Albums");
        assertEquals(testAlbumCategory.getName(), "Rap Albums");
    }

}
