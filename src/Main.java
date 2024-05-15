import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null,
                "Welcome to my tree program! In addition to ouputting tree "
                        + "data, I've attempted to create an algorithm that somewhat "
                        + "effectively draws a tree to the screen.\nIn occasional "
                        + "circumstances, however, it may fail; there could be "
                        + "overlapping lines/nodes or in extremely rare cases just "
                        + "crash entirely.\nIf that happens, all the tree info will "
                        + "still be displayed in the console window.\n\nHope "
                        + "you enjoy!", "Tree", JOptionPane.INFORMATION_MESSAGE);

        BST tree;
        String input;
        boolean first = true;

        do {

            do {
                input = JOptionPane.showInputDialog("Please enter a string of "
                        + "capital letters");

                if (input.isEmpty())
                    JOptionPane.showMessageDialog(null, "You just entered an "
                            + "empty tree! Please try again.");

            } while (input.isEmpty());

            /* ----------------------------------------------------------- */

            tree = new BST(input.substring(0, 1));
            for (int i = 1; i < input.length(); i++)
                tree.insert(input.substring(i, i + 1));

            int treeDepth = tree.getDepth();
            String depth = "Tree Depth: " + treeDepth;
            String leaves = "Tree Leaves: " + tree.getLeaves();
            String height = "Tree Height: " + (treeDepth + 1);
            String width = "Tree Width: " + tree.getWidth();

            System.out.println("Input: " + input + "\n  " + depth + "\n  "
                    + leaves + "\n  " + height + "\n  " + width + "\n");

            tree.draw(input, depth, leaves, height, width);

            /* If first time running, redraws tree to fix drawing issues */
            if (first) {
                tree = new BST(input.substring(0, 1));

                for (int i = 1; i < input.length(); i++)
                    tree.insert(input.substring(i, i + 1));

                tree.draw(input, depth, leaves, height, width);
                first = false;
            }

        } while (JOptionPane.showConfirmDialog(null, "Would you like to run "
                        + "this program again?", null, JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION);
    }

}
