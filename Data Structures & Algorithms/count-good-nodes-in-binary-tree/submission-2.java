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

    int count = 0;

    public int goodNodes(TreeNode root) {
        
        // the idea is if we track the previous level hight node so far
        // and it the current level node is greater than that then the current node is good node
        if (root == null) {
            return 0;
        }

        // dfs(root, Integer.MIN_VALUE);

        // return count;

        return dfs2(root, Integer.MIN_VALUE);

        
    }

    private void dfs(TreeNode root, int maxValue) {
        if (root == null) return;

        if (root.val >= maxValue) {
            count++;
            maxValue = root.val;
        }

        dfs(root.left, maxValue);
        
        dfs(root.right, maxValue);
    
    }

    private int dfs2(TreeNode root, int maxValue) {
        if (root == null) return 0;

        int res = root.val >= maxValue ? 1 : 0;
        maxValue = Math.max(maxValue, root.val);

        res += dfs2(root.left, maxValue);
        res += dfs2(root.right, maxValue);
        return res;
    }

}
