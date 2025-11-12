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

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Album;
import model.AlbumCategory;
import model.Song;

// referenced from SmartHomeUI
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git

// represents the view tab on the navbar that displays album and category info through pressing buttons
@ExcludeFromJacocoGeneratedReport
public class ViewTab extends Tab {

    private static final String FONT = "Times New Roman";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 25;
    private static final String TAB = "    ";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    // EFFECTS: creates a view tab that shows all albums and categories
    public ViewTab() {
        super();
        createSidebar();
        createRefreshButton();
        createAlbumButtons();
        createCategoryButtons();

        setVisible(true);
    }

    // EFFECTS: creates a button for each album that when clicked, a popup window
    // with the album's info appears
    private void createAlbumButtons() {
        List<Album> albums = manager.getAlbumsList();

        for (Album album : albums) {
            JButton button = createButton("View " + album.getName() + " by " + album.getArtist() + " (album)",
                    BUTTON_DIMENSION);
            button.addActionListener(e -> {
                createAlbumPopup(album);
            });
            addToSidebar(button);
        }
    }

    // EFFECTS: creates a button for each category that when clicked, a popup window
    // with info for each album in the category appears
    private void createCategoryButtons() {
        List<AlbumCategory> categories = manager.getAlbumCategoriesList();

        for (AlbumCategory category : categories) {
            JButton button = createButton("View " + category.getName() + " (category)",
                    BUTTON_DIMENSION);
            button.addActionListener(e -> {
                createCategoryPopup(category);

            });
            addToSidebar(button);

        }

    }

    // EFFECTS: creates a pop up window that displays the given album's info
    public void createAlbumPopup(Album album) {

        JFrame popup = new JFrame(album.getName() + " by " + album.getArtist());
        popup.setSize(WIDTH, HEIGHT);

        popup.add(getAlbumInfo(album));

        popup.setLocation(200, 200);
        popup.setVisible(true);
    }

    // EFFECTS: creates a pop up window that displays the given category's info
    private void createCategoryPopup(AlbumCategory category) {

        JFrame popup = new JFrame(category.getName());
        popup.setSize(WIDTH, HEIGHT);

        popup.add(getCategoryInfo(category));

        popup.setLocation(200, 200);
        popup.setVisible(true);
    }

    // EFFECTS: returns a JScrollPane that has labels with info about the given
    // album
    private JScrollPane getAlbumInfo(Album album) {

        JPanel albumEntry = new JPanel();
        albumEntry.setLayout(new BoxLayout(albumEntry, BoxLayout.Y_AXIS));
        albumEntry.setBorder(new EmptyBorder(25, 25, 0, 0));

        addAlbumLabels(albumEntry, album);

        JScrollPane scroll = new JScrollPane(albumEntry, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setBorder(null);

        return scroll;
    }

    // EFFECTS: creates and adds the album field labels
    private void addAlbumLabels(JPanel albumEntry, Album album) {

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

        JLabel reviewLabel = createLabel("Review: " + album.getReview(), WIDTH, HEIGHT / 3, FONT,
                FONT_STYLE,
                FONT_SIZE);
        albumEntry.add(reviewLabel);

        JLabel tracklistLabel = createLabel("Tracklist: ", WIDTH, HEIGHT / 3, FONT,
                FONT_STYLE,
                FONT_SIZE);
        albumEntry.add(tracklistLabel);

        addTracklistLabel(albumEntry, album);
    }

    // EFFECTS: creates and adds tracklist label
    private void addTracklistLabel(JPanel albumEntry, Album album) {
        int position = 1;
        for (Song song : album.getTracklist()) {

            JLabel songLabel = createLabel(TAB + position + ". " + song.getName() + " by " + song.getArtist(), WIDTH,
                    HEIGHT / 3, FONT,
                    FONT_STYLE, FONT_SIZE);
            albumEntry.add(songLabel);
            position++;
        }
    }

    // EFFECTS: displays all created categories using a scroll pane
    private JScrollPane getCategoryInfo(AlbumCategory category) {
        JPanel categoryEntry = new JPanel();
        categoryEntry.setLayout(new BoxLayout(categoryEntry, BoxLayout.Y_AXIS));
        categoryEntry.setBorder(new EmptyBorder(25, 25, 25, 0));

        for (Album album : category.getAlbumList()) {
            categoryEntry.add(getAlbumInfo(album));

        }

        JScrollPane scroll = new JScrollPane(categoryEntry, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setBorder(null);

        return scroll;
    }

    // EFFECTS: creates and returns a label with the given info and font info
    private JLabel createLabel(String text, int width, int height, String font, int style, int size) {
        JLabel label = new JLabel(text);
        label.setSize(width, height);
        label.setFont(new Font(font, style, size));

        return label;
    }

    // EFFECTS: creates a button that when clicked, recreates all buttons to account
    // for any newly added albums
    private void createRefreshButton() {
        JButton button = createButton("Refresh", BUTTON_DIMENSION);
        button.addActionListener(e -> {
            sidebar.removeAll();
            createRefreshButton();
            createAlbumButtons();
            createCategoryButtons();
            repaint();
            revalidate();

        });
        addToSidebar(button);
    }
}
