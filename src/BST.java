// Binary Search Tree class - Jason Sima

import javax.swing.*;

public class BST extends BinaryTree {

    JFrame frame;
    TreeDisplay display;
    private TreeNode root;

    /* Constructor*/
    public BST(String s) {
        root = new TreeNode(s);
        frame = new JFrame("Tree");
        display = new TreeDisplay(root, 1800, 1000, 50);

        frame.setContentPane(display);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /* Inserts a string to the tree */
    public void insert(String item) {
        insertRecur(item, root);
    }

    /* Returns depth of the tree */
    public int getDepth() {
        int leftDepth = 0, rightDepth = 0;

        if (root.getLeft() != null)
            leftDepth = depthRecur(root.getLeft());

        if (root.getRight() != null)
            rightDepth = depthRecur(root.getRight());

        return Math.max(leftDepth, rightDepth);
    }

    /* Returns number of leaves in the tree */
    public int getLeaves() {
        return leavesRecur(root);
    }

    /* Returns width of the tree */
    public int getWidth() {
        return widthRecur(root);
    }

    /* Draws the tree to the screen */
    public void draw(String input, String d, String l, String h, String w) {
        display.drawTree(input, d, l, h, w);
    }

    /* Helper method for inserting tree nodes */
    private TreeNode insertRecur(String s, TreeNode node) {
        if (node == null)
            return new TreeNode (s, null, null);
        else if (s.compareTo(node.getValue()) <= 0)
            node.setLeft(insertRecur(s, node.getLeft()));
        else
            node.setRight(insertRecur(s, node.getRight()));

        return node;
    }

    /* Helper method for recursively finding depth of tree */
    private int depthRecur(TreeNode node) {
        if (node == null)
            return 0;
        else {
            int left = depthRecur(node.getLeft());
            int right = depthRecur(node.getRight());

            return (left > right) ? left + 1 : right + 1;
        }
    }

    /* Helper method for recursively finding leaves of tree */
    private int leavesRecur(TreeNode node) {
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        else {
            int left = 0, right = 0;

            if (node.getLeft() != null)
                left = leavesRecur(node.getLeft());
            if (node.getRight() != null)
                right = leavesRecur(node.getRight());

            return left + right;
        }
    }

    /* Helper method for recursively finding width of tree */
    private int widthRecur(TreeNode node) {
        int left = 0, right = 0;

        if (node.getLeft() != null)
            left = widthRecur(node.getLeft());
        if (node.getRight() != null)
            right = widthRecur(node.getRight());

        int mid = 1 + depthRecur(node.getLeft()) + depthRecur(node.getRight());

        return Math.max(Math.max(left, right), mid);
    }

}
