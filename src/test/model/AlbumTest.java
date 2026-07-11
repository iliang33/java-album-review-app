package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Set of tests for the Album class
public class AlbumTest {
    private Album testAlbum;
    private Album otherTestAlbum;
    private Song testSong;
    private Song otherTestSong;
    private Song oneMoreTestSong;

    @BeforeEach
    void runBefore() {
        testAlbum = new Album("Speak Now", "Taylor Swift", "Pop Rock", 9.1, "");
        otherTestAlbum = new Album("1989", "Taylor Swift", "Pop", 7.4, "");
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
        assertEquals(testAlbum.trackListToString(), "\t1. Mine by Taylor Swift");
    }

    @Test
    void testTrackListToStringThreeSongs() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        testAlbum.addSong(oneMoreTestSong);

        assertEquals(testAlbum.trackListToString(),
                "\t1. Mine by Taylor Swift\n\t2. Sparks Fly by Taylor Swift\n\t3. Dear John by Taylor Swift");
    }

    @Test
    void testSetArtist() {
        testAlbum.setArtist("Daya");
        assertEquals(testAlbum.getArtist(), "Daya");

        testAlbum.setArtist("Sza");
        assertEquals(testAlbum.getArtist(), "Sza");
    }

    @Test
    void testSetName() {
        testAlbum.setName("Red");
        assertEquals(testAlbum.getName(), "Red");

        testAlbum.setName("Fearless");
        assertEquals(testAlbum.getName(), "Fearless");
    }

    @Test
    void testSetGenre() {
        testAlbum.setGenre("Soul");
        assertEquals(testAlbum.getGenre(), "Soul");

        testAlbum.setGenre("Folk");
        assertEquals(testAlbum.getGenre(), "Folk");
    }

    @Test
    void testSetRating() {
        testAlbum.setRating(0.0);
        assertEquals(testAlbum.getRating(), 0.0);

        testAlbum.setRating(5.0);
        assertEquals(testAlbum.getRating(), 5.0);

        testAlbum.setRating(10.0);
        assertEquals(testAlbum.getRating(), 10.0);
    }

    @Test
    void testSetReview() {
        testAlbum.setReview("Good lyrics");
        assertEquals(testAlbum.getReview(), "Good lyrics");

        testAlbum.setReview("Great vocals");
        assertEquals(testAlbum.getReview(), "Great vocals");
    }

    @Test
    void testMergeAlbumBothEmpty() {
        testAlbum.mergeAlbum(otherTestAlbum);
        assertTrue(testAlbum.getTracklist().isEmpty());
        assertTrue(otherTestAlbum.getTracklist().isEmpty());

    }

    @Test
    void testMergeAlbumNoDuplicates() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);

        otherTestAlbum.addSong(oneMoreTestSong);

        testAlbum.mergeAlbum(otherTestAlbum);
        assertEquals(testAlbum.getTracklist().size(), 3);
        assertEquals(testAlbum.getTracklist().get(2), oneMoreTestSong);
        assertEquals(otherTestAlbum.getTracklist().size(), 1);

    }

    @Test
    void testMergeAlbumWithDuplicates() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);

        otherTestAlbum.addSong(otherTestSong);
        otherTestAlbum.addSong(oneMoreTestSong);

        testAlbum.mergeAlbum(otherTestAlbum);
        assertEquals(testAlbum.getTracklist().size(), 3);
        assertEquals(testAlbum.getTracklist().get(2), oneMoreTestSong);
        assertEquals(otherTestAlbum.getTracklist().size(), 2);

    }
}
