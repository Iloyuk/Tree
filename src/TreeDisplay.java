// TreeDisplay class - Jason Sima

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;

public class TreeDisplay extends JPanel {

    private int dispWidth, dispHeight, nodeRadius;
    private boolean error;
    private String input, depth, leaves, height, width;

    /* Lists to keep track of nodes and lines when drawing the tree */
    private CopyOnWriteArrayList<CircleNode> leftNodes, rightNodes;
    private CopyOnWriteArrayList<Line> leftLines, rightLines;
    private TreeNode root;

    /* Constructor */
    public TreeDisplay(TreeNode node, int w, int h, int r) {
        super();
        root = node;
        dispWidth = w;
        dispHeight = h;
        nodeRadius = r;

        leftNodes = new CopyOnWriteArrayList<CircleNode>();
        rightNodes = new CopyOnWriteArrayList<CircleNode>();
        leftLines = new CopyOnWriteArrayList<Line>();
        rightLines = new CopyOnWriteArrayList<Line>();
        error = false;
    }

    /* Returns dimensions of the display */
    public Dimension getPreferredSize() {
        return new Dimension(dispWidth, dispHeight);
    }

    /* Main method to draw the tree onto the display */
    public void drawTree(String i, String d, String l, String h, String w) {
        input = i;
        depth = d;
        leaves = l;
        height = h;
        width = w;

        boolean success = true, normalize = true;
        int iterations = 0;

        /* Draws left subtree */
        if (root.getLeft() != null) {
            double slope = 1;

            do {
                if (normalize && slope >= 5.5) {
                    normalize = false;
                    slope = 1;
                }

                leftLines.add(new Line(dispWidth/2, 2 * nodeRadius,
                        (int)(dispWidth/2 - (slope * nodeRadius)),
                        3 * nodeRadius));

                drawLeftSubtree(root.getLeft(),
                        (int)(dispWidth/2 - ((1 + slope) * nodeRadius)),
                        3 * nodeRadius, nodeRadius, slope, normalize);

                /* Continues redrawing until tree doesn't cross midpoint */
                for (CircleNode circle : leftNodes)
                    if (circle.getX() > dispWidth/2 - nodeRadius) {
                        success = false;
                        iterations++;
                        slope += 0.4;
                        leftLines.clear();
                        leftNodes.clear();
                        break;
                    }
                    else
                        success = true;

            } while (!success && iterations <= 100);
        }

        /* Draws right subtree */
        if (root.getRight() != null && iterations <= 100) {
            double slope = 1;
            success = true;
            normalize = true;
            iterations = 0;

            do {
                if (normalize && slope >= 5.5) {
                    normalize = false;
                    slope = 1;
                }

                rightLines.add(new Line(dispWidth/2 + nodeRadius,
                        2 * nodeRadius,
                        (int)(dispWidth/2 + ((1 + slope) * nodeRadius)),
                        3 * nodeRadius));

                drawRightSubtree(root.getRight(),
                        (int)(dispWidth/2 + ((1 + slope) * nodeRadius)),
                        3 * nodeRadius, nodeRadius, slope, normalize);

                for (CircleNode circle : rightNodes)
                    if (circle.getX() < dispWidth/2 + nodeRadius) {
                        success = false;
                        iterations++;
                        slope += 0.4;
                        rightLines.clear();
                        rightNodes.clear();
                        break;
                    }
                    else
                        success = true;

            } while (!success && iterations <= 100);
        }

        /* Hardstops after a certain point */
        if (iterations > 100)
            error = true;

    }

    /* Helper method for drawing the left subtree */
    private void drawLeftSubtree(TreeNode node, int x, int y, int r,
                                 double slope, boolean normalize) {

        leftNodes.add(new CircleNode(x, y, r, node.getValue()));
        slope *= 0.45;

        if (normalize && slope < 0.35)
            slope += 0.8;
        else if (!normalize)
            slope *= 0.75;

        if (node.getLeft() != null) {
            leftLines.add(new Line(x, y + r, (int)(x - (slope * r)),
                    y + (2 * r)));
            drawLeftSubtree(node.getLeft(), (int)(x - ((1 + slope) * r)),
                    y + (2 * r), r, slope, normalize);
        }

        if (node.getRight() != null) {
            leftLines.add(new Line(x + r, y + r, (int)(x + ((1 + slope) * r)),
                    y + (2 * r)));
            drawLeftSubtree(node.getRight(), (int)(x + ((1 + slope) * r)),
                    y + (2 * r), r, slope, normalize);
        }

    }

    /* Helper method for drawing the right subtree */
    private void drawRightSubtree(TreeNode node, int x, int y, int r,
                                  double slope, boolean normalize) {

        rightNodes.add(new CircleNode(x, y, r, node.getValue()));
        slope *= 0.45;

        if (normalize && slope < 0.35)
            slope += 0.8;
        else if (!normalize)
            slope *= 0.75;

        if (node.getLeft() != null) {
            rightLines.add(new Line(x, y + r, (int)(x - (slope * r)),
                    y + (2 * r)));
            drawRightSubtree(node.getLeft(), (int)(x - ((1 + slope) * r)),
                    y + (2 * r), r, slope, normalize);
        }

        if (node.getRight() != null) {
            rightLines.add(new Line(x + r, y + r, (int)(x + ((1 + slope) * r)),
                    y + (2 * r)));
            drawRightSubtree(node.getRight(), (int)(x + ((1 + slope) * r)),
                    y + (2 * r), r, slope, normalize);
        }
    }

    /* Draws all the shapes and tree info to the screen */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.ITALIC, 28));
        g2.drawString("Input: " + input, 20, 37);

        g2.setFont(new Font("SansSerif", Font.BOLD, 40));
        g2.drawString(depth, 20, 85);
        g2.drawString(leaves, 20, 135);
        g2.drawString(height, 20, 185);
        g2.drawString(width, 20, 235);

        if (!error) {
            leftNodes.add(new CircleNode(dispWidth/2, 50, nodeRadius,
                    root.getValue()));

            for (CircleNode node : leftNodes)
                node.drawCircle(g2);

            for (CircleNode node : rightNodes)
                node.drawCircle(g2);

            for (Line line : leftLines)
                line.drawLine(g2);

            for (Line line : rightLines)
                line.drawLine(g2);
        }
        else {
            g2.setFont(new Font("SansSerif", Font.BOLD, 42));
            g2.drawString("Tree was too complex to draw :(",
                    (int)(dispWidth/3.5), dispHeight/2);
        }
    }

}