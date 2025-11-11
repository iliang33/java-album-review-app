package ui.tabs;

import java.awt.Color;
import java.awt.Graphics;

import model.ReviewManager;


// referenced from SimpleDrawingPlayer-Starter
//https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter.git

public class StatsTab extends Tab {

    public StatsTab(ReviewManager manager) {
        super(manager);
        repaint();
    }



    // EFFECTS: draws a bar graph that shows how many albums falls into different
    // rating ranges
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(200, 200, 25, 100);
        g.setColor(Color.ORANGE);
        g.fillRect(200, 200, 26, 101);
       
    }

}
