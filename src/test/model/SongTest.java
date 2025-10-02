package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SongTest {
    private Song testSong;

    @BeforeEach
    void runBefore() {
        testSong = new Song("ceilings", "Lizzy McAlpine", "3:02", 9.0, "");

    }

    @Test
    void testConstructor() {
        assertEquals(testSong.getName(), "ceilings");
        assertEquals(testSong.getArtist(), "Lizzy McAlpine");
        assertEquals(testSong.getLength(), "3:02");
        assertEquals(testSong.getRating(), 9.0);
        assertEquals(testSong.getReview(), "");
    }

    @Test
    void testIncreaseRating() {
        testSong.increaseRating(0.5);
        assertEquals(testSong.getRating(), 9.5);
    }

    @Test
    void testIncreaseRatingMultipleTimes() {
        testSong.increaseRating(0.5);
        assertEquals(testSong.getRating(), 9.5);

        testSong.increaseRating(0.5);
        assertEquals(testSong.getRating(), 10.0);
    }

    @Test
    void testDecreaseRating() {
        testSong.decreaseRating(8.5);
        assertEquals(testSong.getRating(), 0.5);
    }

    @Test
    void testDecreaseRatingMultipleTimes() {
        testSong.decreaseRating(8.5);
        assertEquals(testSong.getRating(), 0.5);

        testSong.decreaseRating(0.5);
        assertEquals(testSong.getRating(), 0.0);
    }

    @Test
    void testToString() {
        assertTrue(testSong.toString()
                .contains("Name: ceilings, Artist: Lizzy McAlpine, Length: 3:02, Rating: 9.0, Review: \"\""));
    }
}
