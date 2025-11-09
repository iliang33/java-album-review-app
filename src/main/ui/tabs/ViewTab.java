package ui.tabs;

import java.awt.*;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Album;
import model.AlbumCategory;
import model.ReviewManager;
import model.Song;
import ui.AlbumReviewGUI;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

public class ViewTab extends Tab {

    private static final String FONT = "Times New Roman";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 25;
    private static final String TAB = "    ";

    private AlbumReviewGUI gui;

    // EFFECTS: creates a view tab that shows all albums and categories
    public ViewTab(ReviewManager manager, AlbumReviewGUI gui) {
        super(manager);
        this.gui = gui;
        // showCreatedReviews();
        createSidebar();
        createAlbumButtons();
        createCategoryButtons();

        setVisible(true);

    }

    // EFFECTS: creates a button for each album that when clicked, a popup window
    // with the album's info appears
    private void createAlbumButtons() {
        List<Album> albums = manager.getAlbumsList();

        for (Album album : albums) {
            JButton button = createButton("View " + album.getName() + " by " + album.getArtist() + " info",
                    BUTTON_DIMENSION);
            button.addActionListener(e -> {
                createAlbumPopup(album);

            });

        }

    }

    // EFFECTS: creates a button for each category that when clicked, a popup window
    // with info for each album in the category appears
    private void createCategoryButtons() {

        // use a scroll pane for the pop up window

        List<AlbumCategory> categories = manager.getAlbumCategoriesList();

        for (AlbumCategory category : categories) {
            JButton button = createButton("View " + category.getName() + " info",
                    BUTTON_DIMENSION);
            button.addActionListener(e -> {
                createCategoryPopup(category);

            });

        }

    }

    // EFFECTS: creates a pop up window that displays the given album's info
    private void createAlbumPopup(Album album) {

        JFrame popup = new JFrame(album.getName() + " by " + album.getArtist());
        popup.setSize(gui.WIDTH, gui.HEIGHT);

        popup.add(getAlbumInfo(album));

        popup.setLocation(200, 200);
        popup.setVisible(true);
    }

    // EFFECTS: creates a pop up window that displays the given category's info
    private void createCategoryPopup(AlbumCategory category) {

        JFrame popup = new JFrame(category.getName());
        popup.setSize(gui.WIDTH, gui.HEIGHT);

        popup.add(getCategoryInfo(category));

        popup.setLocation(200, 200);
        popup.setVisible(true);
    }

    // EFFECTS: returns a JPanel that has labels with info about the given album
    private JPanel getAlbumInfo(Album album) {

        JPanel albumEntry = new JPanel();
        albumEntry.setLayout(new BoxLayout(albumEntry, BoxLayout.Y_AXIS));
        albumEntry.setBorder(new EmptyBorder(25, 25, 0, 0));

        JLabel nameLabel = createLabel("Name: " + album.getName(), WIDTH, HEIGHT / 3, FONT, FONT_STYLE, FONT_SIZE);
        albumEntry.add(nameLabel);

        JLabel artistLabel = createLabel("Artist: " + album.getArtist(), WIDTH, HEIGHT / 3, FONT, FONT_STYLE,
                FONT_SIZE);
        albumEntry.add(artistLabel);

        JLabel genreLabel = createLabel("Genre: " + album.getGenre(), WIDTH, HEIGHT / 3, FONT, FONT_STYLE, FONT_SIZE);
        albumEntry.add(genreLabel);

        JLabel ratingLabel = createLabel("Rating: " + Double.toString(album.getRating()), WIDTH, HEIGHT / 3, FONT,
                FONT_STYLE,
                FONT_SIZE);
        albumEntry.add(ratingLabel);

        JLabel tracklistLabel = createLabel("Tracklist: ", WIDTH, HEIGHT / 3, FONT,
                FONT_STYLE,
                FONT_SIZE);
        albumEntry.add(tracklistLabel);

        int position = 1;
        for (Song song : album.getTracklist()) {

            JLabel songLabel = createLabel(TAB + position + ". " + song.getName() + " by " + song.getArtist(), WIDTH,
                    HEIGHT / 3, FONT,
                    FONT_STYLE, FONT_SIZE);
            albumEntry.add(songLabel);
            position++;

        }

        return albumEntry;

    }

    // EFFECTS: displays all created categories
    private JScrollPane getCategoryInfo(AlbumCategory category) {

        JPanel categoryEntry = new JPanel();
        categoryEntry.setLayout(new BoxLayout(categoryEntry, BoxLayout.Y_AXIS));
        categoryEntry.setBorder(new EmptyBorder(25, 25, 25, 0));

        for(Album album : category.getAlbumList()){
            categoryEntry.add(getAlbumInfo(album));


        }

        JScrollPane scroll = new JScrollPane(categoryEntry, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scroll;

    }

    // EFFECTS: creates and returns a label with the given info
    private JLabel createLabel(String text, int width, int height, String font, int style, int size) {
        JLabel label = new JLabel(text);
        label.setSize(width, height);
        label.setFont(new Font(font, style, size));

        return label;

    }

}
