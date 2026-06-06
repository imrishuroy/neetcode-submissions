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
    public int maxSum;

    // build the path sub from top to bottom using dfs, but if the sum < 0, reset the sum to 0 
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;

        dfs(root);

        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = Math.max(dfs(node.left), 0); // if the left sum is negative neglate that and take 0

        int rightSum = Math.max(dfs(node.right), 0);

        maxSum = Math.max(maxSum, leftSum + rightSum + node.val);

        return node.val + Math.max(leftSum, rightSum);

    }
}
