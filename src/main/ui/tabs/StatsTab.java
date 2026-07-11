package ui.tabs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import model.Album;

// referenced from SimpleDrawingPlayer-Starter
//https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter.git

// represents the stats tab on the navbar where graphs relating to album statistics is shown
public class StatsTab extends Tab {
    private static final int numOfRanges = 5;
    private static final String RANGE1 = "0.0-5.0";
    private static final String RANGE2 = "5.1-6.9";
    private static final String RANGE3 = "7.0-7.9";
    private static final String RANGE4 = "8.0-8.9";
    private static final String RANGE5 = "9.0-10.0";

    private static final int BEGIN_INDEX_LOWER_BOUND = 0;
    private static final int END_INDEX_LOWER_BOUND_EXCLUSIVE = 3;
    private static final int BEGIN_INDEX_UPPER_BOUND = 4;

    private static final Double RANGE1_LOWER_BOUND = Double
            .parseDouble(RANGE1.substring(BEGIN_INDEX_LOWER_BOUND, END_INDEX_LOWER_BOUND_EXCLUSIVE));
    private static final Double RANGE1_UPPER_BOUND = Double.parseDouble(RANGE1.substring(BEGIN_INDEX_UPPER_BOUND));

    private static final Double RANGE2_LOWER_BOUND = Double
            .parseDouble(RANGE2.substring(BEGIN_INDEX_LOWER_BOUND, END_INDEX_LOWER_BOUND_EXCLUSIVE));
    private static final Double RANGE2_UPPER_BOUND = Double.parseDouble(RANGE2.substring(BEGIN_INDEX_UPPER_BOUND));

    private static final Double RANGE3_LOWER_BOUND = Double
            .parseDouble(RANGE3.substring(BEGIN_INDEX_LOWER_BOUND, END_INDEX_LOWER_BOUND_EXCLUSIVE));
    private static final Double RANGE3_UPPER_BOUND = Double.parseDouble(RANGE3.substring(BEGIN_INDEX_UPPER_BOUND));

    private static final Double RANGE4_LOWER_BOUND = Double
            .parseDouble(RANGE4.substring(BEGIN_INDEX_LOWER_BOUND, END_INDEX_LOWER_BOUND_EXCLUSIVE));
    private static final Double RANGE4_UPPER_BOUND = Double.parseDouble(RANGE4.substring(BEGIN_INDEX_UPPER_BOUND));

    private static final Double RANGE5_LOWER_BOUND = Double
            .parseDouble(RANGE5.substring(BEGIN_INDEX_LOWER_BOUND, END_INDEX_LOWER_BOUND_EXCLUSIVE));
    private static final Double RANGE5_UPPER_BOUND = Double.parseDouble(RANGE5.substring(BEGIN_INDEX_UPPER_BOUND));

    private static final int VERTICAL_SPACING = 50;
    private static final int LEFT_PADDING = 25;
    private static final int BAR_HEIGHT = 25;
    private static final float MAX_BAR_WIDTH = 300;

    protected static final String FONT = "Lato";
    protected static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 18;

    private static final String barGraphTitle = "Album Ratings Distribution";

    private List<String> ranges;
    private int numInRange1 = 0;
    private int numInRange2 = 0;
    private int numInRange3 = 0;
    private int numInRange4 = 0;
    private int numInRange5 = 0;
    private boolean clearScreen = false;

    // EFFECTS: creates a stats tab containing a bar graph showing how many albums
    // have ratings that fall into certain ranges
    public StatsTab() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(VERTICAL_SPACING, LEFT_PADDING, 0, 0));

        ranges = new ArrayList<>();
        ranges.add(RANGE1);
        ranges.add(RANGE2);
        ranges.add(RANGE3);
        ranges.add(RANGE4);
        ranges.add(RANGE5);

        setupNumInRanges();

        repaint();
        drawRanges();
        createRefreshButton();
    }

    // MODFIES: this
    // EFFECTS: draws a bar graph that shows the number of albums that falls into
    // different ranges based on their rating
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!clearScreen) {
            List<Integer> numsInRanges = new ArrayList<>();

            numsInRanges.add(numInRange1);
            numsInRanges.add(numInRange2);
            numsInRanges.add(numInRange3);
            numsInRanges.add(numInRange4);
            numsInRanges.add(numInRange5);

            for (int i = 0; i < numOfRanges; i++) {
                int totalNumOfAlbums = manager.getAlbumsList().size();
                int barWidth = Math.round((numsInRanges.get(i) / (float) totalNumOfAlbums) * MAX_BAR_WIDTH);

                g.setColor(Color.ORANGE);

                int posX = LEFT_PADDING + FONT_SIZE * 5;
                int posY = VERTICAL_SPACING * (i + 1) + 24 * i;

                g.drawRect(posX, posY, barWidth, BAR_HEIGHT);
                g.fillRect(posX, posY, barWidth + 1, BAR_HEIGHT + 1);

                g.setColor(Color.BLACK);
                g.setFont(new Font(FONT, FONT_STYLE, FONT_SIZE));

                g.drawString(numsInRanges.get(i).toString(), posX + barWidth + 15, posY + BAR_HEIGHT / 2);
                g.drawString(barGraphTitle, Math.round(MAX_BAR_WIDTH / 2), VERTICAL_SPACING / 2);

            }

        }

    }

    // EFFECTS: draws the labels for the rating ranges
    public void drawRanges() {
        for (int i = 0; i < 5; i++) {
            JLabel range = new JLabel(ranges.get(i));
            range.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
            add(range);
            add(Box.createVerticalStrut(VERTICAL_SPACING));

        }

    }

    // MODIFIES: this
    // EFFECTS: gets the number of album ratings that fall in each range and sets
    // the corresponding fields to their respective numbers
    public void setupNumInRanges() {
        List<Album> albums = manager.getAlbumsList();

        if (!albums.isEmpty()) {
            for (Album album : albums) {
                Double rating = album.getRating();
                if (RANGE1_LOWER_BOUND <= rating && rating <= RANGE1_UPPER_BOUND) {
                    numInRange1++;

                } else if (RANGE2_LOWER_BOUND <= rating && rating <= RANGE2_UPPER_BOUND) {
                    numInRange2++;

                } else if (RANGE3_LOWER_BOUND <= rating && rating <= RANGE3_UPPER_BOUND) {
                    numInRange3++;

                } else if (RANGE4_LOWER_BOUND <= rating && rating <= RANGE4_UPPER_BOUND) {
                    numInRange4++;

                } else if (RANGE5_LOWER_BOUND <= rating && rating <= RANGE5_UPPER_BOUND) {
                    numInRange5++;

                }

            }

        }

    }

    // MODIFIES: this
    // EFFECTS: creates a button that when clicked, recreates graph to account
    // for any newly added albums
    private void createRefreshButton() {
        JButton button = createButton("Refresh", BUTTON_DIMENSION);
        button.addActionListener(e -> {
            removeAll();
            resetNumInRange();
            drawRanges();
            setupNumInRanges();
            createRefreshButton();
            clearScreen = true;
            repaint();
            clearScreen = false;
            repaint();

        });

        add(button);

    }

    // MODIFIES: this
    // EFFECTS: resets tracking of how many albums are in each rating range
    private void resetNumInRange() {
        numInRange1 = 0;
        numInRange2 = 0;
        numInRange3 = 0;
        numInRange4 = 0;
        numInRange5 = 0;

    }

}
