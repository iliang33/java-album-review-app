package ui;

import java.io.FileNotFoundException;

import javax.swing.*;

import model.ReviewManager;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.AlbumsTab;
import ui.tabs.CategoriesTab;
import ui.tabs.HomeTab;
import ui.tabs.StatsTab;
import ui.tabs.UpdateTab;
import ui.tabs.ViewTab;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the GUI for the album review app
public class AlbumReviewGUI extends JFrame {
    private static final int HOME_TAB_INDEX = 0;
    private static final int ALBUMS_TAB_INDEX = 1;
    private static final int CATEGORIES_TAB_INDEX = 2;
    private static final int UPDATE_TAB_INDEX = 3;
    private static final int VIEW_TAB_INDEX = 4;
    private static final int STATS_TAB_INDEX = 5;
    public final int WIDTH = 600;
    public final int HEIGHT = 600;
    private final String JSON_SAVE_FILE = "./data/ReviewManager.json";

    private JTabbedPane navbar;
    protected ReviewManager manager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates AlbumReviewGUI, initializes ReviewManager and shows navbar
    // with its tabs
    public AlbumReviewGUI() throws FileNotFoundException {
        super("Album Review App");

        jsonWriter = new JsonWriter(JSON_SAVE_FILE);
        jsonReader = new JsonReader(JSON_SAVE_FILE);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        manager = new ReviewManager();

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
        JPanel statsTab= new StatsTab(manager);

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

        navbar.add(statsTab, STATS_TAB_INDEX);
        navbar.setTitleAt(STATS_TAB_INDEX, "Stats");

    }

    public JsonWriter getJsonWriter() {
        return this.jsonWriter;
    }

    public JsonReader getJsonReader() {
        return this.jsonReader;
    }

    public String getJsonSaveFile() {
        return JSON_SAVE_FILE;
    }

    public ReviewManager getReviewManager() {
        return this.manager;
    }

    public void setReviewManager(ReviewManager manager) {
        this.manager = manager;
    }

}
