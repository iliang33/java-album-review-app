package ui;

import javax.swing.*;

import model.Album;
import model.AlbumCategory;
import model.ReviewManager;
import model.Song;
import ui.tabs.AlbumsTab;
import ui.tabs.CategoriesTab;
import ui.tabs.HomeTab;
import ui.tabs.UpdateTab;
import ui.tabs.ViewTab;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the GUI for the album review app
public class AlbumReviewGUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int ALBUMS_TAB_INDEX = 1;
    public static final int CATEGORIES_TAB_INDEX = 2;
    public static final int UPDATE_TAB_INDEX = 3;
    public static final int VIEW_TAB_INDEX = 4;

    public final int WIDTH = 600;
    public final int HEIGHT = 600;
    private JTabbedPane navbar;
    private ReviewManager manager;

    // EFFECTS: creates AlbumReviewGUI, initializes ReviewManager and shows navbar
    // with its tabs
    public AlbumReviewGUI() {
        super("Album Review App");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Album album1 = new Album("Speak Now", "Taylor Swift", "Pop Rock", 9.1, "Good");
        Album album2 = new Album("Melodrama", "Lorde", "Alt-Pop", 8.5, "Cool");
        Album album3 = new Album("The Rise and Fall of a Midwest Princess", "Chappell Roan", "Dance-Pop", 8.3, "Fun");
        Album album4 = new Album("GNX", "Kendrick Lamar", "Rap", 8.2, "aoty");

        Song song1 = new Song("Mine", "Taylor Swift", 9, "");
        Song song2 = new Song("Sparks Fly", "Taylor Swift", 9.5, "");
        Song song3 = new Song("Dear John", "Taylor Swift", 9, "");

        AlbumCategory cat1 = new AlbumCategory("pop1");
        AlbumCategory cat2 = new AlbumCategory("pop2");
        AlbumCategory cat3 = new AlbumCategory("pop3");

        album1.addSong(song1);
        album1.addSong(song2);
        album1.addSong(song3);

        album2.addSong(song1);
        album2.addSong(song2);
        album2.addSong(song3);

        album3.addSong(song1);
        album3.addSong(song2);
        album3.addSong(song3);

        album4.addSong(song1);
        album4.addSong(song2);
        album4.addSong(song3);

        cat1.addAlbum(album1);
        cat1.addAlbum(album2);
        cat1.addAlbum(album3);
        cat1.addAlbum(album4);

        manager = new ReviewManager();

        manager.addAlbum(album1);
        manager.addAlbum(album2);
        manager.addAlbum(album3);
        manager.addAlbum(album4);

        manager.addCategory(cat1);
        manager.addCategory(cat2);
        manager.addCategory(cat3);

        navbar = new JTabbedPane();
        navbar.setTabPlacement(JTabbedPane.TOP);

        showTabs();
        add(navbar);

        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: adds home, albums, categories, update, and view tabs to the navbar
    public void showTabs() {
        JPanel homeTab = new HomeTab(manager, this);
        JPanel albumsTab = new AlbumsTab(manager);
        JPanel categoriesTab = new CategoriesTab(manager);
        JPanel updateTab = new UpdateTab(manager);
        JPanel viewTab = new ViewTab(manager, this);

        navbar.add(homeTab, HOME_TAB_INDEX);
        navbar.setTitleAt(HOME_TAB_INDEX, "Home");

        navbar.add(albumsTab, ALBUMS_TAB_INDEX);
        navbar.setTitleAt(ALBUMS_TAB_INDEX, "Albums");

        navbar.add(categoriesTab, CATEGORIES_TAB_INDEX);
        navbar.setTitleAt(CATEGORIES_TAB_INDEX, "Categories");

        navbar.add(updateTab, UPDATE_TAB_INDEX);
        navbar.setTitleAt(UPDATE_TAB_INDEX, "Update");

        navbar.add(viewTab, VIEW_TAB_INDEX);
        navbar.setTitleAt(VIEW_TAB_INDEX, "View");

    }

    public ReviewManager getReviewManager() {
        return this.manager;

    }

    public JTabbedPane getNavbar() {
        return this.navbar;
    }

}
