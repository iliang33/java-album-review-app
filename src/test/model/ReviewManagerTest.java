package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Set of tests for the ReviewManager class
@ExcludeFromJacocoGeneratedReport
public class ReviewManagerTest {
    private ReviewManager manager;
    private Album testAlbum;
    private Album otherTestAlbum;
    private Album anotherTestAlbum;
    private Album extraTestAlbum;
    private Album lastTestAlbum;
    AlbumCategory testAlbumCategory;
    AlbumCategory otherAlbumCategory;
    private Song testSong;
    private Song otherTestSong;

    @BeforeEach
    void runBefore() {
        manager = new ReviewManager();
        testAlbum = new Album("Speak Now", "Taylor Swift", "Pop Rock", 9.1, "");
        otherTestAlbum = new Album("1989", "Taylor Swift", "Pop", 7.4, "");
        anotherTestAlbum = new Album("Eternal Sunshine", "Ariana Grande", "Pop", 8.0, "");
        extraTestAlbum = new Album("Speak Now", "Adele", "Pop", 8.0, "");
        lastTestAlbum = new Album("21", "Adele", "Pop Soul", 8.3, "");
        testAlbumCategory = new AlbumCategory("Pop Albums");
        otherAlbumCategory = new AlbumCategory("Pop 2");
        testSong = new Song("Mine", "Taylor Swift", 9, "");
        otherTestSong = new Song("Sparks Fly", "Taylor Swift", 9.5, "");
    }

    @Test
    void testConstructor() {
        assertTrue(manager.getAlbumsList().isEmpty());
        assertTrue(manager.getAlbumCategoriesList().isEmpty());

    }

    @Test
    void testAddAlbum() {
        manager.addAlbum(testAlbum);

        assertEquals(manager.getAlbumsList().get(0), testAlbum);
        assertEquals(manager.getAlbumsList().size(), 1);

        manager.addAlbum(otherTestAlbum);

        assertEquals(manager.getAlbumsList().get(1), otherTestAlbum);
        assertEquals(manager.getAlbumsList().size(), 2);

    }

    @Test
    void testRemoveAlbum() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(lastTestAlbum);

        manager.removeAlbum("Melodrama", "Lorde");
        assertFalse(manager.getAlbumsList().contains(testAlbum));
        assertEquals(manager.getAlbumsList().size(), 2);

        manager.removeAlbum("After Laughter", "Paramore");
        assertFalse(manager.getAlbumsList().contains(otherTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 1);

        manager.removeAlbum("21", "Adele");
        assertFalse(manager.getAlbumsList().contains(lastTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 0);

    }

    @Test
    void testRemoveAlbumCasing() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(lastTestAlbum);

        manager.removeAlbum("AFTER LaughtER", "ParaMORE");
        assertFalse(manager.getAlbumsList().contains(otherTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 2);

    }

    @Test
    void testRemoveAlbumLastFirst() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(lastTestAlbum);

        manager.removeAlbum("21", "Adele");
        assertFalse(manager.getAlbumsList().contains(lastTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 2);

        manager.removeAlbum("Melodrama", "Lorde");
        assertFalse(manager.getAlbumsList().contains(testAlbum));
        assertEquals(manager.getAlbumsList().size(), 1);

        manager.removeAlbum("After Laughter", "Paramore");
        assertFalse(manager.getAlbumsList().contains(otherTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 0);

    }

    @Test
    void testRemoveAlbumMiddleFirst() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(lastTestAlbum);

        manager.removeAlbum("After Laughter", "Paramore");
        assertFalse(manager.getAlbumsList().contains(otherTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 2);

        manager.removeAlbum("Melodrama", "Lorde");
        assertFalse(manager.getAlbumsList().contains(testAlbum));
        assertEquals(manager.getAlbumsList().size(), 1);

        manager.removeAlbum("21", "Adele");
        assertFalse(manager.getAlbumsList().contains(lastTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 0);

    }

    @Test
    void testRemoveAlbumNotInAlbumsList() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(lastTestAlbum);

        manager.removeAlbum("Folklore", "Taylor Swift");

        assertTrue(manager.getAlbumsList().contains(otherTestAlbum));
        assertTrue(manager.getAlbumsList().contains(testAlbum));
        assertTrue(manager.getAlbumsList().contains(lastTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 3);

    }

    @Test
    void testRemoveAlbumWhereOneOfGivenNameAndArtistIsInTheAlbumsList() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(lastTestAlbum);

        manager.removeAlbum("Melodrama", "Taylor Swift");

        assertTrue(manager.getAlbumsList().contains(otherTestAlbum));
        assertTrue(manager.getAlbumsList().contains(testAlbum));
        assertTrue(manager.getAlbumsList().contains(lastTestAlbum));
        assertEquals(manager.getAlbumsList().size(), 3);

    }

    @Test
    void testRemoveAlbumFromEmptyList() {
        manager.removeAlbum("The life of a showgirl", "Taylor Swift");

        assertTrue(manager.getAlbumsList().isEmpty());

    }

    @Test
    void testAddToAlbumTracklist() {
        manager.addToAlbumTracklist("Speak Now", "Taylor Swift", "Long Live", "Taylor Swift", 9, "Wow");
        manager.addToAlbumTracklist("Speak Now", "Taylor Swift", "Enchanted", "Taylor Swift", 10, "Perfect");

        Song song1 = testAlbum.getTracklist().get(0);
        Song song2 = testAlbum.getTracklist().get(1);

        assertEquals(testAlbum.getTracklist().size(), 2);

        assertEquals(song1.getName(), "Long Live");
        assertEquals(song1.getArtist(), "Taylor Swift");
        assertEquals(song1.getRating(), 9);
        assertEquals(song1.getReview(), "Wow");

        assertEquals(testAlbum.getTracklist().size(), 2);
        assertEquals(song2.getName(), "Enchanted");
        assertEquals(song2.getArtist(), "Taylor Swift");
        assertEquals(song2.getRating(), 10);
        assertEquals(song2.getReview(), "Perfect");
    }

    @Test
    void testRemoveFromAlbumTracklistLastFirst() {
        manager.addToAlbumTracklist("Speak Now", "Taylor Swift", "Long Live", "Taylor Swift", 9, "Wow");
        manager.addToAlbumTracklist("Speak Now", "Taylor Swift", "Enchanted", "Taylor Swift", 10, "Perfect");

        manager.removeFromAlbumTracklist("Speak Now", "Taylor Swift", 2);

        Song song1 = testAlbum.getTracklist().get(0);

        assertEquals(testAlbum.getTracklist().size(), 1);

        assertEquals(song1.getName(), "Long Live");
        assertEquals(song1.getArtist(), "Taylor Swift");
        assertEquals(song1.getRating(), 9);
        assertEquals(song1.getReview(), "Wow");

        manager.removeFromAlbumTracklist("Speak Now", "Taylor Swift", 1);
        assertEquals(testAlbum.getTracklist().size(), 0);
    }

    @Test
    void testRemoveFromAlbumTracklistFirstFirst() {
        manager.addToAlbumTracklist("Speak Now", "Taylor Swift", "Long Live", "Taylor Swift", 9, "Wow");
        manager.addToAlbumTracklist("Speak Now", "Taylor Swift", "Enchanted", "Taylor Swift", 10, "Perfect");

        manager.removeFromAlbumTracklist("Speak Now", "Taylor Swift", 1);

        Song song = testAlbum.getTracklist().get(0);

        assertEquals(testAlbum.getTracklist().size(), 1);

        assertEquals(song.getName(), "Enchanted");
        assertEquals(song.getArtist(), "Taylor Swift");
        assertEquals(song.getRating(), 10);
        assertEquals(song.getReview(), "Perfect");

        manager.removeFromAlbumTracklist("Speak Now", "Taylor Swift", 1);
        assertEquals(testAlbum.getTracklist().size(), 0);
    }

    @Test
    void testAddCategory() {
        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        assertEquals(manager.getAlbumCategoriesList().size(), 2);

        assertEquals(manager.getAlbumCategoriesList().get(0), testAlbumCategory);
        assertEquals(manager.getAlbumCategoriesList().get(1), otherAlbumCategory);

    }

    @Test
    void testRemoveCategoryLastFirst() {
        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        manager.removeCategory("Pop 2");

        assertEquals(manager.getAlbumCategoriesList().size(), 1);
        assertEquals(manager.getAlbumCategoriesList().get(0), testAlbumCategory);

        manager.removeCategory("Pop Albums");

        assertEquals(manager.getAlbumCategoriesList().size(), 0);

    }

    @Test
    void testRemoveCategoryFirstFirst() {
        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        manager.removeCategory("Pop Albums");

        assertEquals(manager.getAlbumCategoriesList().size(), 1);
        assertEquals(manager.getAlbumCategoriesList().get(0), otherAlbumCategory);

        manager.removeCategory("Pop 2");

        assertEquals(manager.getAlbumCategoriesList().size(), 0);

    }

    @Test
    void testAddToCategory() {
        manager.addToCategory("Pop Albums", "Speak Now", "Taylor Swift");
        manager.addToCategory("Pop Albums", "21", "Adele");

        List<Album> albumList = testAlbumCategory.getAlbumList();

        assertEquals(albumList.size(), 2);

        assertEquals(albumList.get(0), testAlbum);
        assertEquals(albumList.get(1), lastTestAlbum);
    }

    @Test
    void testRemoveFromCategoryLastFirst() {
        manager.addToCategory("Pop Albums", "Speak Now", "Taylor Swift");
        manager.addToCategory("Pop Albums", "21", "Adele");

        manager.removeFromCategory("Pop Albums", "21", "Adele");

        assertEquals(testAlbumCategory.getAlbumList().size(), 1);
        assertEquals(testAlbumCategory.getAlbumList().get(0), testAlbum);

        manager.removeFromCategory("Pop Albums", "Speak Now", "Taylor Swift");

        assertEquals(testAlbumCategory.getAlbumList().size(), 0);

    }

    @Test
    void testRemoveFromCategoryFirstFirst() {
        manager.addToCategory("Pop Albums", "Speak Now", "Taylor Swift");
        manager.addToCategory("Pop Albums", "21", "Adele");

        manager.removeFromCategory("Pop Albums", "Speak Now", "Taylor Swift");

        assertEquals(testAlbumCategory.getAlbumList().size(), 1);
        assertEquals(testAlbumCategory.getAlbumList().get(0), lastTestAlbum);

        manager.removeFromCategory("Pop Albums", "21", "Adele");

        assertEquals(testAlbumCategory.getAlbumList().size(), 0);

    }

    @Test
    void testSortAlbumsByAlphabeticalArtistAlreadySorted() {
        manager.addAlbum(lastTestAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(testAlbum);

        manager.sortAlbumsByAlphabeticalArtist();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), lastTestAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), testAlbum);
    }

    @Test
    void testSortAlbumsByAlphabeticalArtistTwoOfSameArtist() {
        manager.addAlbum(lastTestAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(testAlbum);

        manager.sortAlbumsByAlphabeticalArtist();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), lastTestAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), testAlbum);

        manager.addAlbum(otherTestAlbum);
        manager.sortAlbumsByAlphabeticalArtist();

        List<Album> albums2 = manager.getAlbumsList();

        assertEquals(albums2.size(), 4);
        assertEquals(albums2.get(0), lastTestAlbum);
        assertEquals(albums2.get(1), anotherTestAlbum);
        assertEquals(albums2.get(2), testAlbum);
        assertEquals(albums2.get(3), otherTestAlbum);
    }

    @Test
    void testSortAlbumsByAlphabeticalArtistNotAlreadySorted() {
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(testAlbum);
        manager.addAlbum(lastTestAlbum);

        manager.sortAlbumsByAlphabeticalArtist();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), lastTestAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), testAlbum);
    }

    @Test
    void testSortAlbumsByAlphabeticalNameAlreadySorted() {
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(testAlbum);

        manager.sortAlbumsByAlphabeticalName();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), otherTestAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), testAlbum);
    }

    @Test
    void testSortAlbumsByAlphabeticalNameTwoOfSameName() {
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(testAlbum);

        manager.sortAlbumsByAlphabeticalName();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), otherTestAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), testAlbum);

        manager.addAlbum(extraTestAlbum);
        manager.sortAlbumsByAlphabeticalName();

        List<Album> albums2 = manager.getAlbumsList();

        assertEquals(albums2.size(), 4);
        assertEquals(albums2.get(0), otherTestAlbum);
        assertEquals(albums2.get(1), anotherTestAlbum);
        assertEquals(albums2.get(2), testAlbum);
        assertEquals(albums2.get(3), extraTestAlbum);
    }

    @Test
    void testSortAlbumsByAlphabeticalNameNotAlreadySorted() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(otherTestAlbum);

        manager.sortAlbumsByAlphabeticalName();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), otherTestAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), testAlbum);
    }

    @Test
    void testSortAlbumsByRatingAlreadySorted() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(otherTestAlbum);

        manager.sortAlbumsByRating();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), testAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), otherTestAlbum);

    }

    @Test
    void testSortAlbumsByRatingTwoOfSameRating() {
        manager.addAlbum(testAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(otherTestAlbum);

        manager.sortAlbumsByRating();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), testAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), otherTestAlbum);

        manager.addAlbum(extraTestAlbum);
        manager.sortAlbumsByRating();

        List<Album> albums2 = manager.getAlbumsList();

        assertEquals(albums2.size(), 4);
        assertEquals(albums2.get(0), testAlbum);
        assertEquals(albums2.get(1), anotherTestAlbum);
        assertEquals(albums2.get(2), extraTestAlbum);
        assertEquals(albums2.get(3), otherTestAlbum);

    }

    @Test
    void testSortAlbumsByRatingNotAlreadySorted() {
        manager.addAlbum(otherTestAlbum);
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(testAlbum);

        manager.sortAlbumsByRating();

        List<Album> albums = manager.getAlbumsList();

        assertEquals(albums.size(), 3);
        assertEquals(albums.get(0), testAlbum);
        assertEquals(albums.get(1), anotherTestAlbum);
        assertEquals(albums.get(2), otherTestAlbum);

    }

    @Test
    void testGetWantedAlbumFound() {
        manager.addAlbum(anotherTestAlbum);
        manager.addAlbum(testAlbum);
        Album album = manager.getWantedAlbum("Speak Now", "Taylor Swift");

        assertEquals(album, testAlbum);

    }

    @Test
    void testGetWantedAlbumNotFoundEmptyList() {
        Album album = manager.getWantedAlbum("Speak Now", "Taylor Swift");

        assertNull(album);

    }

    @Test
    void testGetWantedAlbumNotFoundBothNameAndArtist() {
        manager.addAlbum(anotherTestAlbum);
        Album album = manager.getWantedAlbum("Speak Now", "Taylor Swift");

        assertNull(album);

    }

    @Test
    void testGetWantedAlbumNotFoundOneOfNameAndArtistCorrect() {
        manager.addAlbum(otherTestAlbum);
        Album album = manager.getWantedAlbum("Speak Now", "Taylor Swift");

        assertNull(album);

    }

    @Test
    void testGetWantedCategoryFound() {
        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        AlbumCategory category = manager.getWantedCategory("Pop Albums");

        assertEquals(category, testAlbumCategory);

    }

    @Test
    void testGetWantedCategoryNotFoundEmptyList() {

        AlbumCategory category = manager.getWantedCategory("Pop Albums");

        assertNull(category);

    }

    @Test
    void testGetWantedCategoryNotFound() {
        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        AlbumCategory category = manager.getWantedCategory("bad");

        assertNull(category);
    }

    @Test
    void testGetWantedAlbumInWantedCategoryFound() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);

        manager.addCategory(testAlbumCategory);

        Album album = manager.getWantedAlbumInWantedCategory("Speak Now", "Taylor Swift", testAlbumCategory);

        assertEquals(album, testAlbum);
    }

    @Test
    void testGetWantedAlbumInWantedCategoryNotFound() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);

        manager.addCategory(testAlbumCategory);

        Album album = manager.getWantedAlbumInWantedCategory("Life Support", "Madison Beer", testAlbumCategory);

        assertNull(album);
    }

    @Test
    void testGetWantedAlbumInWantedCategoryNotFoundOneOfNameArtistCorrect() {
        testAlbumCategory.addAlbum(testAlbum);
        testAlbumCategory.addAlbum(otherTestAlbum);

        manager.addCategory(testAlbumCategory);

        Album album = manager.getWantedAlbumInWantedCategory("Lover", "Taylor Swift", testAlbumCategory);

        assertNull(album);
    }

    @Test
    void testGetWantedAlbumInWantedCategoryEmptyAlbumList() {
        manager.addCategory(testAlbumCategory);

        Album album = manager.getWantedAlbumInWantedCategory("Beatopia", "beabadoobee", testAlbumCategory);

        assertNull(album);
    }

    @Test
    void testGetWantedSongInTracklistFound() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);

        manager.addAlbum(testAlbum);

        Song song = manager.getWantedSongInTracklist("Mine", testAlbum);

        assertEquals(song, testSong);
    }

    @Test
    void testGetWantedSongInTracklistNotFoundEmptyTracklist() {
        manager.addAlbum(testAlbum);
        Song song = manager.getWantedSongInTracklist("Mine", testAlbum);

        assertNull(song);
    }

    @Test
    void testGetWantedSongInTracklistNotFoundNotEmptyTracklist() {
        testAlbum.addSong(testSong);
        testAlbum.addSong(otherTestSong);
        manager.addAlbum(testAlbum);
        Song song = manager.getWantedSongInTracklist("Timeless", testAlbum);

        assertNull(song);
    }

    @Test
    void testAlbumIsInAnyOneCategory(){
        testAlbumCategory.addAlbum(testAlbum);

        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        assertTrue(manager.albumIsInAnyCategory(testAlbum));

    }

    @Test
    void testAlbumIsInAnyTwoCategories(){
        testAlbumCategory.addAlbum(testAlbum);
        otherAlbumCategory.addAlbum(testAlbum);

        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        assertTrue(manager.albumIsInAnyCategory(testAlbum));

    }

    @Test
    void testAlbumIsNotInAnyCategoryEmptyAlbumList(){
        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        assertFalse(manager.albumIsInAnyCategory(testAlbum));

    }

    @Test
    void testAlbumIsNotInAnyCategoryNonEmptyAlbumList(){
        testAlbumCategory.addAlbum(testAlbum);
        otherAlbumCategory.addAlbum(testAlbum);

        manager.addCategory(testAlbumCategory);
        manager.addCategory(otherAlbumCategory);

        assertFalse(manager.albumIsInAnyCategory(otherTestAlbum));

    }
}
