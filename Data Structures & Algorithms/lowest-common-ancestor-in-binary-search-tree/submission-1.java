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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        // if p.val <= root.val && q.val => root.val -> LCA - root (return root)
        // if p.val > root.val && q.val > root.val -> check right of root
        // if p.val < root.val && q.val < root -> check left of root

        return dfs(root, p, q);

    }

    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.val <= root.val && q.val >= root.val) {
            return root;
        }
        
        else if (p.val < root.val && q.val < root.val) {
            return dfs(root.left, p, q);
        } else if(p.val > root.val && q.val > root.val)
            return dfs(root.right, p, q);

        else {
            return root;
        }
    }









}
