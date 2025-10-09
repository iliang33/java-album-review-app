package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlbumTest {
    private Album testAlbum;
    private Song testSong;
    private Song otherTestSong;
    private Song oneMoreTestSong;

    @BeforeEach
    void runBefore() {
        testAlbum = new Album("Speak Now", "Taylor Swift", "Pop Rock", 9.1, "");
        testSong = new Song("Mine", "Taylor Swift", 9, "");
        otherTestSong = new Song("Sparks Fly", "Taylor Swift", 9.5, "");
        oneMoreTestSong = new Song("Dear John", "Taylor Swift", 9, "");
    }

    @Test
    void testConstructor() {
        assertEquals(testAlbum.getName(), "Speak Now");
        assertEquals(testAlbum.getArtist(), "Taylor Swift");
        assertEquals(testAlbum.getGenre(), "Pop Rock");
        assertTrue(testAlbum.getTracklist().isEmpty());
        assertEquals(testAlbum.getRating(), 9.1);
        assertEquals(testAlbum.getReview(), "");

    }

    @Test
    void testAddSong() {
        testAlbum.addSong(testSong);
        assertEquals(testAlbum.getTracklist().size(), 1);
        assertTrue(testSong.equals(testAlbum.getTracklist().get(0)));

        testAlbum.addSong(otherTestSong);
        assertEquals(testAlbum.getTracklist().size(), 2);
        assertTrue(otherTestSong.equals(testAlbum.getTracklist().get(1)));
    }

    @Test
    void testRemoveSong() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        testAlbum.removeSong("Mine", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(testSong));
        assertEquals(testAlbum.getTracklist().size(), 2);

        testAlbum.removeSong("Sparks Fly", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(otherTestSong));
        assertEquals(testAlbum.getTracklist().size(), 1);

        testAlbum.removeSong("Dear John", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(oneMoreTestSong));
        assertEquals(testAlbum.getTracklist().size(), 0);

    }

    @Test
    void testRemoveSongLastFirst() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        testAlbum.removeSong("Dear John", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(oneMoreTestSong));
        assertEquals(testAlbum.getTracklist().size(), 2);

        testAlbum.removeSong("Mine", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(testSong));
        assertEquals(testAlbum.getTracklist().size(), 1);

        testAlbum.removeSong("Sparks Fly", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(otherTestSong));
        assertEquals(testAlbum.getTracklist().size(), 0);

    }

    @Test
    void testRemoveSongMiddleFirst() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        testAlbum.removeSong("Sparks Fly", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(otherTestSong));
        assertEquals(testAlbum.getTracklist().size(), 2);

        testAlbum.removeSong("Dear John", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(oneMoreTestSong));
        assertEquals(testAlbum.getTracklist().size(), 1);

        testAlbum.removeSong("Mine", "Taylor Swift");
        assertFalse(testAlbum.getTracklist().contains(testSong));
        assertEquals(testAlbum.getTracklist().size(), 0);

    }

    @Test
    void testRemoveSongInEmptyTracklist() {
        assertTrue(testAlbum.getTracklist().isEmpty());
        testAlbum.removeSong("Sparks Fly", "Taylor Swift");
        assertTrue(testAlbum.getTracklist().isEmpty());

    }

    @Test
    void testRemoveSongNotInTracklist() {
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        testAlbum.removeSong("Mine", "Taylor Swift");

        assertTrue(testAlbum.getTracklist().contains(otherTestSong));
        assertTrue(testAlbum.getTracklist().contains(oneMoreTestSong));

        assertEquals(testAlbum.getTracklist().size(), 2);

    }

    @Test
    void testCreateSong() {
        Song newSong = testAlbum.createSong("Back To December", "Taylor Swift", 10, "");
        Song anotherSong = testAlbum.createSong("Speak Now", "Taylor Swift", 9, "");

        assertEquals(newSong.getName(), "Back To December");
        assertEquals(newSong.getArtist(), "Taylor Swift");
        assertEquals(newSong.getRating(), 10);
        assertEquals(newSong.getReview(), "");

        assertEquals(anotherSong.getName(), "Speak Now");
        assertEquals(anotherSong.getArtist(), "Taylor Swift");
        assertEquals(anotherSong.getRating(), 9);
        assertEquals(anotherSong.getReview(), "");
    }


    @Test
    void testToString() {
        assertTrue(testAlbum.toString()
                .contains(
                        "Name: Speak Now\nArtist: Taylor Swift\nGenre: Pop Rock"
                                + "\nRating: 9.1\nReview: "));
    }

    @Test
    void testTrackListToStringNoSongs() {
        assertEquals(testAlbum.trackListToString(), "");
    }

    @Test
    void testTrackListToStringOneSong() {
        testAlbum.addSong(testSong);
        assertEquals(testAlbum.trackListToString(), "1. Mine by Taylor Swift");
    }

    @Test
    void testTrackListToStringThreeSongs() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        assertEquals(testAlbum.trackListToString(),
                "1. Mine by Taylor Swift\n2. Sparks Fly by Taylor Swift\n3. Dear John by Taylor Swift");
    }

}
