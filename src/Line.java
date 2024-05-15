// Line class - Jason Sima

import java.awt.*;
import java.awt.geom.Line2D;

public class Line {

    private int x1, y1;
    private int x2, y2;

    /* Constructor */
    public Line(int x1Coord, int y1Coord, int x2Coord, int y2Coord) {
        x1 = x1Coord;
        y1 = y1Coord;

        x2 = x2Coord;
        y2 = y2Coord;
    }

    /* Draws line connecting (x1, y1) to (x2, y2) */
    public void drawLine(Graphics2D g) {
        g.draw(new Line2D.Float(x1, y1, x2, y2));
    }

}