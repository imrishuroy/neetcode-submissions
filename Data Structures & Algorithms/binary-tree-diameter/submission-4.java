/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        // if we calculate the height of the tree from all the node and take max of all
        diameter = 0;

        dfs(root);

        return diameter;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int leftHeight = dfs(node.left); // What's the tallest path on my left?

        int rightHeight = dfs(node.right); // What's the tallest path on my right?

        diameter = Math.max(diameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
