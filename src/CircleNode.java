// CircleNode class - Jason Sima

import java.awt.*;

public class CircleNode {

    private int x, y, r;
    private String l;

    /* Constructor */
    public CircleNode(int xCoord, int yCoord, int radius, String letter) {
        x = xCoord;
        y = yCoord;
        r = radius;
        l = letter;
    }

    /* Returns top left x position of circle */
    public int getX() {
        return x;
    }

    /* Returns top left y position of circle */
    public int getY() {
        return y;
    }

    /* Draws circle with radius r at topleft being (x, y) */
    public void drawCircle(Graphics2D g) {
        g.drawOval(x, y, r, r);
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.drawString(l, (int)(x + (0.37 * r)), (int)(y + (0.67 * r)));
    }

}