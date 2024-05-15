public class TreeNode {

    private String value;
    private TreeNode left;
    private TreeNode right;

    /* Constructor */
    public TreeNode(String initValue) {
        value = initValue;
        left = null;
        right = null;
    }

    /* Overloaded Constructor */
    public TreeNode(String initValue, TreeNode initLeft, TreeNode initRight) {
        value = initValue;
        left = initLeft;
        right = initRight;
    }


    /* GET METHODS */

    public String getValue() { return value; }

    public TreeNode getLeft() { return left; }

    public TreeNode getRight() { return right; }


    /* SET METHODS */

    public void setValue(String theNewValue) { value = theNewValue; }

    public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }

    public void setRight(TreeNode theNewRight) { right = theNewRight; }

}
