package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Set of tests for the Song class
@ExcludeFromJacocoGeneratedReport
public class SongTest {
    private Song testSong;

    @BeforeEach
    void runBefore() {
        testSong = new Song("ceilings", "Lizzy McAlpine", 9.0, "");

    }

    @Test
    void testConstructor() {
        assertEquals(testSong.getName(), "ceilings");
        assertEquals(testSong.getArtist(), "Lizzy McAlpine");
        assertEquals(testSong.getRating(), 9.0);
        assertEquals(testSong.getReview(), "");
    }

    @Test
    void testToString() {
        assertTrue(testSong.toString()
                .contains("Name: ceilings\nArtist: Lizzy McAlpine\nRating: 9.0\nReview: "));
    }
}
