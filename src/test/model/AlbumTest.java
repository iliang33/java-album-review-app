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
        testSong = new Song("Mine", "Taylor Swift", 3, 51, 9, "");
        otherTestSong = new Song("Sparks Fly", "Taylor Swift", 4, 21, 9.5, "");
        oneMoreTestSong = new Song("Dear John", "Taylor Swift", 6, 45, 9, "");
    }

    @Test
    void testConstructor() {
        assertEquals(testAlbum.getName(), "Speak Now");
        assertEquals(testAlbum.getArtist(), "Taylor Swift");
        assertEquals(testAlbum.getGenre(), "Pop Rock");
        assertTrue(testAlbum.getTracklist().isEmpty());
        assertEquals(testAlbum.getNumberOfSongs(), 0);
        assertEquals(testAlbum.getLengthMinsPart(), 0);
        assertEquals(testAlbum.getLengthSecsPart(), 0);
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
        Song newSong = testAlbum.createSong("Back To December", "Taylor Swift", 4, 54, 10, "");
        Song anotherSong = testAlbum.createSong("Speak Now", "Taylor Swift", 4, 02, 9, "");

        assertEquals(newSong.getName(), "Back To December");
        assertEquals(newSong.getArtist(), "Taylor Swift");
        assertEquals(newSong.getLengthMinsPart(), 4);
        assertEquals(newSong.getLengthSecsPart(), 54);
        assertEquals(newSong.getRating(), 10);
        assertEquals(newSong.getReview(), "");

        assertEquals(anotherSong.getName(), "Speak Now");
        assertEquals(anotherSong.getArtist(), "Taylor Swift");
        assertEquals(anotherSong.getLengthMinsPart(), 4);
        assertEquals(anotherSong.getLengthSecsPart(), 02);
        assertEquals(anotherSong.getRating(), 9);
        assertEquals(anotherSong.getReview(), "");
    }

    @Test
    void testGetAverageRatingNoSongs() {
        assertEquals(testAlbum.getAverageRating(), 0);

    }

    @Test
    void testGetAverageRatingOneSong() {
        testAlbum.addSong(testSong);

        assertEquals(testAlbum.getAverageRating(), 9.0);

    }

    @Test
    void testGetAverageRatingThreeSongs() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        assertEquals(testAlbum.getAverageRating(), 9.2);

    }

    @Test
    void setLengthNoSongs() {
        testAlbum.setLength();

        assertEquals(testAlbum.getLengthMinsPart(), 0);
        assertEquals(testAlbum.getLengthSecsPart(), 0);

    }

    @Test
    void setLengthOneSong() {
        testAlbum.addSong(testSong);

        testAlbum.setLength();

        assertEquals(testAlbum.getLengthMinsPart(), 3);
        assertEquals(testAlbum.getLengthSecsPart(), 51);

    }

    @Test
    void setLengthThreeSongs() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        testAlbum.setLength();

        assertEquals(testAlbum.getLengthMinsPart(), 14);
        assertEquals(testAlbum.getLengthSecsPart(), 57);

    }

    @Test
    void testToString() {
        assertTrue(testAlbum.toString()
                .contains(
                        "Name: Speak Now\nArtist: Taylor Swift\nGenre: Pop Rock"
                                + "\nNumber of songs: 0\nLength: 0 mins 0 secs\nRating: 9.1\nReview: "));
    }

    @Test
    void testTrackListToStringNoSongs() {
        assertEquals(testAlbum.trackListToString(), "");
    }

    @Test
    void testTrackListToStringOneSong() {
        testAlbum.addSong(testSong);
        assertEquals(testAlbum.trackListToString(), "1. Mine");
    }

    @Test
    void testTrackListToStringThreeSongs() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        assertEquals(testAlbum.trackListToString(), "1. Mine\n2. Sparks Fly\n3. Dear John");
    }

}
