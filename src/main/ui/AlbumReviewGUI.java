package ui;

import java.io.FileNotFoundException;
import java.awt.event.*;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Event;
import model.EventLog;
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

// GUI Album Review Application
@ExcludeFromJacocoGeneratedReport
public class AlbumReviewGUI extends JFrame implements WindowListener {
    private static final int HOME_TAB_INDEX = 0;
    private static final int ALBUMS_TAB_INDEX = 1;
    private static final int CATEGORIES_TAB_INDEX = 2;
    private static final int UPDATE_TAB_INDEX = 3;
    private static final int VIEW_TAB_INDEX = 4;
    private static final int STATS_TAB_INDEX = 5;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static final String JSON_SAVE_FILE = "./data/ReviewManager.json";

    private JTabbedPane navbar;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates AlbumReviewGUI, initializes ReviewManager, JsonWriter, and
    // JsonReader, and shows a navbar with its tabs
    public AlbumReviewGUI() throws FileNotFoundException {
        super("Album Review App");

        jsonWriter = new JsonWriter(JSON_SAVE_FILE);
        jsonReader = new JsonReader(JSON_SAVE_FILE);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        navbar = new JTabbedPane();
        navbar.setTabPlacement(JTabbedPane.TOP);

        showTabs();
        add(navbar);
        addWindowListener(this);

        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: adds home, albums, categories, update, view, and stats tabs to the
    // navbar
    public void showTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel albumsTab = new AlbumsTab();
        JPanel categoriesTab = new CategoriesTab();
        JPanel updateTab = new UpdateTab();
        JPanel viewTab = new ViewTab();
        JPanel statsTab = new StatsTab();

        navbar.add(homeTab, HOME_TAB_INDEX);
        navbar.setTitleAt(HOME_TAB_INDEX, "Home");

        navbar.add(albumsTab, ALBUMS_TAB_INDEX);
        navbar.setTitleAt(ALBUMS_TAB_INDEX, "Albums");

        navbar.add(categoriesTab, CATEGORIES_TAB_INDEX);
        navbar.setTitleAt(CATEGORIES_TAB_INDEX, "Categories");

        navbar.add(updateTab, UPDATE_TAB_INDEX);
        navbar.setTitleAt(UPDATE_TAB_INDEX, "Update");

        navbar.add(new JScrollPane(viewTab), VIEW_TAB_INDEX);
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

    @Override
    public void windowOpened(WindowEvent e) {
        // unused
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // unused
    }

    @Override
    public void windowClosed(WindowEvent e) {
        EventLog eventLog = EventLog.getInstance();

        for (Event event : eventLog) {
            System.out.println(event + "\n");

        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // unused);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // unused
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // unused
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // unused
    }

}
