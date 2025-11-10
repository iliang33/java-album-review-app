package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a manager that stores and handles operations on all created album review and album categories
public class ReviewManager implements Writable {
    private List<Album> albums;
    private List<AlbumCategory> categories;

    // EFFECTS: creates a review manager with no stored album reviews or album
    // categories
    public ReviewManager() {
        this.albums = new ArrayList<>();
        this.categories = new ArrayList<>();

    }

    // REQUIRES: album is not already in list
    // MODIFIES: this
    // EFFECTS: adds given album to all albums list
    public void addAlbum(Album album) {
        this.albums.add(album);

    }

    // REQUIRES: the album exists
    // MODIFIES: this
    // EFFECTS: removes given album from all albums list
    public void removeAlbum(Album album) {
        this.albums.remove(album);

    }

    // REQUIRES: album exists and song is not already in list
    // EFFECTS: creates and adds song with the given info to the tracklist of the
    // given album
    public void addToAlbumTracklist(Album album, String songName, String songArtist,
            double songRating, String songReview) {
        this.albums.get(getIndexOfAlbum(album))
                .addSong(new Song(songName, songArtist, songRating, songReview));

    }

    // REQUIRES: songNumber >= 1 && songNumber <= tracklist size and the album
    // exists
    // EFFECTS: removes the song in the given position number in tracklist from
    // given album
    public void removeFromAlbumTracklist(Album album, int songNumber) {
        this.albums.get(getIndexOfAlbum(album)).getTracklist().remove(songNumber - 1);
    }

    // REQUIRES: category is not already in list
    // MODIFIES: this
    // EFFECTS: adds given category to all categories list
    public void addCategory(AlbumCategory category) {
        this.categories.add(category);

    }

    // REQUIRES: the category exists
    // MODIFIES: this
    // EFFECTS: removes given category from all categories list
    public void removeCategory(AlbumCategory category) {
        this.categories.remove(category);

    }

    // REQUIRES: category exists and album is not already in category
    // EFFECTS: adds album with the given info to given category
    public void addToCategory(AlbumCategory category, String albumName, String albumArtist) {
        this.categories.get(getIndexOfCategory(category))
                .addAlbum(getWantedAlbum(albumName, albumArtist));
    }

    // REQUIRES: the album and the category both exist
    // MODIFIES: this
    // EFFECTS: removes the album with given info from the given category
    public void removeFromCategory(AlbumCategory category, String albumName, String albumArtist) {
        this.categories.get(getIndexOfCategory(category)).removeAlbum(albumName, albumArtist);

    }

    // MODIFIES: this
    // EFFECTS: removes the given album (referenced by name and artist) from all
    // categories
    public void removeFromAllCategories(String albumName, String artistName) {
        for (AlbumCategory category : getAlbumCategoriesList()) {
            Album album = getWantedAlbumInWantedCategory(albumName, artistName, category);
            if (album != null) {
                category.removeAlbum(albumName, artistName);

            }
        }

    }

    // MODIFIES: this
    // EFFECTS: sorts album list by artist alphabetically. If two albums have the
    // same artist, the order does not matter
    public void sortAlbumsByAlphabeticalArtist() {
        for (int i = 0; i < this.albums.size(); i++) {
            for (int j = 0; j < this.albums.size(); j++) {
                // a negative result from compareTo means the string on the left should go
                // before the string on the right
                if (this.albums.get(i).getArtist().compareToIgnoreCase(this.albums.get(j).getArtist()) < 0) {
                    // stores the album so it doesn't get lost during swapping
                    Album currentAlbumComparingToOthers = this.albums.get(i);

                    // swap positions
                    this.albums.set(i, this.albums.get(j));
                    this.albums.set(j, currentAlbumComparingToOthers);

                }
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: sorts album list by name alphabetically. If two albums have the same
    // name, the order does not matter
    public void sortAlbumsByAlphabeticalName() {
        for (int i = 0; i < this.albums.size(); i++) {
            for (int j = 0; j < this.albums.size(); j++) {
                // a negative result from compareTo means the string on the left should go
                // before the string on the right
                if (this.albums.get(i).getName().compareToIgnoreCase(this.albums.get(j).getName()) < 0) {
                    // stores the album so it doesn't get lost during swapping
                    Album currentAlbumComparingToOthers = this.albums.get(i);

                    // swap positions
                    this.albums.set(i, this.albums.get(j));
                    this.albums.set(j, currentAlbumComparingToOthers);

                }
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: sorts album list by rating high to low. if two albums have same
    // rating, the order does not matter
    public void sortAlbumsByRating() {
        for (int i = 0; i < this.albums.size(); i++) {
            for (int j = 0; j < this.albums.size(); j++) {

                if (this.albums.get(i).getRating() > this.albums.get(j).getRating()) {
                    // stores the album so it doesn't get lost during swapping
                    Album currentAlbumComparingToOthers = this.albums.get(i);

                    // swap positions
                    this.albums.set(i, this.albums.get(j));
                    this.albums.set(j, currentAlbumComparingToOthers);

                }
            }

        }
    }

    // EFFECTS: return album specified by name and artist, null if not found
    public Album getWantedAlbum(String name, String artist) {
        Album wantedAlbum = null;
        for (Album album : this.albums) {
            if (album.getName().equalsIgnoreCase(name) && album.getArtist().equalsIgnoreCase(artist)) {
                wantedAlbum = album;
            }

        }
        return wantedAlbum;

    }

    // EFFECTS: return category specified by name, null if not found
    public AlbumCategory getWantedCategory(String name) {
        AlbumCategory wantedCategory = null;
        for (AlbumCategory category : this.categories) {
            if (category.getName().equals(name)) {
                wantedCategory = category;
            }

        }
        return wantedCategory;

    }

    // EFFECTS: return album specified by name and artist in given category, null if
    // not found
    public Album getWantedAlbumInWantedCategory(String name, String artist, AlbumCategory category) {
        Album wantedAlbum = null;
        for (Album album : category.getAlbumList()) {
            if (album.getName().equalsIgnoreCase(name) && album.getArtist().equalsIgnoreCase(artist)) {
                wantedAlbum = album;
            }

        }
        return wantedAlbum;
    }

    // EFFECTS: return song specified by name and artist that is in the given
    // album's tracklist, null if not found
    public Song getWantedSongInTracklist(String name, String artist, Album album) {
        Song wantedSong = null;
        for (Song song : album.getTracklist()) {
            if (song.getName().equalsIgnoreCase(name)) {
                wantedSong = song;
            }
        }
        return wantedSong;
    }

    // EFFECTS: returns true if the given album is in any category, false otherwise
    public boolean albumIsInAnyCategory(Album album) {
        for (AlbumCategory category : this.categories) {
            if (category.getAlbumList().contains(album)) {
                return true;

            }

        }
        return false;
    }

    public List<Album> getAlbumsList() {
        return this.albums;

    }

    public List<AlbumCategory> getAlbumCategoriesList() {
        return this.categories;

    }

    // REQUIRES: the given album is in the all albums list
    // EFFECTS: returns the index of the given album in the albums list
    public int getIndexOfAlbum(Album album) {
        int index = 0;
        for (int i = 0; i < this.albums.size(); i++) {
            if (this.albums.get(i).equals(album)) {
                index = i;

            }

        }
        return index;
    }

    // REQUIRES: the given category is in the all album categories list
    // EFFECTS: returns the index of the given category in the categories list
    public int getIndexOfCategory(AlbumCategory category) {
        int index = 0;
        for (int i = 0; i < this.categories.size(); i++) {
            if (this.categories.get(i).equals(category)) {
                index = i;
            }

        }
        return index;
    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns this review manager as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("albums", albumsToJson());
        json.put("categories", categoriesToJson());
        return json;
    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns albums in this this review manager as a JSON array
    private JSONArray albumsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Album album : this.albums) {
            jsonArray.put(album.toJson());
        }

        return jsonArray;
    }

    // referenced from JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: returns categories in this this review manager as a JSON array
    private JSONArray categoriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (AlbumCategory category : this.categories) {
            jsonArray.put(category.toJson());
        }

        return jsonArray;
    }

}
