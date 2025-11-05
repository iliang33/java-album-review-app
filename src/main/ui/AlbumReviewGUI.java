package ui;

import javax.swing.*;

import model.ReviewManager;
import ui.tabs.AlbumsTab;
import ui.tabs.CategoriesTab;
import ui.tabs.HomeTab;
import ui.tabs.UpdateTab;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the GUI for the album review app
public class AlbumReviewGUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int ALBUMS_TAB_INDEX = 1;
    public static final int CATEGORIES_TAB_INDEX = 2;
    public static final int UPDATE_TAB_INDEX = 3;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JTabbedPane navbar;
    private ReviewManager manager;

    // EFFECTS: creates AlbumReviewGUI, initializes ReviewManager and shows navbar
    // with its tabs
    public AlbumReviewGUI() {
        super("Album Review App");
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
    // EFFECTS: adds home, albums, categories, and update tabs to the navbar
    public void showTabs() {
        JPanel homeTab = new HomeTab(manager);
        JPanel albumsTab = new AlbumsTab(manager);
        JPanel categoriesTab = new CategoriesTab(manager);
        JPanel updateTab = new UpdateTab(manager);

        navbar.add(homeTab, HOME_TAB_INDEX);
        navbar.setTitleAt(HOME_TAB_INDEX, "Home");

        navbar.add(albumsTab, ALBUMS_TAB_INDEX);
        navbar.setTitleAt(ALBUMS_TAB_INDEX, "Albums");

        navbar.add(categoriesTab, CATEGORIES_TAB_INDEX);
        navbar.setTitleAt(CATEGORIES_TAB_INDEX, "Categories");

        navbar.add(updateTab, UPDATE_TAB_INDEX);
        navbar.setTitleAt(UPDATE_TAB_INDEX, "Update");

    }

    public ReviewManager getReviewManager() {
        return this.manager;

    }

    public JTabbedPane getNavbar() {
        return this.navbar;
    }

}
