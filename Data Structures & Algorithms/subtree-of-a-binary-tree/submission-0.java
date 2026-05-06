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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);

    }

    private boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;

        if (root.val == subRoot.val) {
            if (sameTree(root, subRoot)) {
                return true;
            }
        }

        return dfs(root.left, subRoot) || dfs(root.right, subRoot);
    }

    private boolean sameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        return sameTree(p.left, q.left) && sameTree(p.right, q.right);
    }
}
