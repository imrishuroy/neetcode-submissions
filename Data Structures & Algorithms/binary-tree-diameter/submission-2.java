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

    private void dfs(TreeNode node) {
        if (node == null)
            return;

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        diameter = Math.max(diameter, leftHeight + rightHeight);

        dfs(node.left);
        dfs(node.right);
    }

    private int height(TreeNode node) {
        if (node == null)
            return 0;

        return 1 + Math.max(height(node.left), height(node.right));
    }
}
