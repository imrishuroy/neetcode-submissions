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

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes p and q in a BST.
     *
     * Intuition:
     * Because this is a BST, we can decide whether both nodes lie
     * in the left subtree, right subtree, or on different sides
     * by simply comparing values with the current root.
     *
     * This allows us to skip entire subtrees and search efficiently.

     TC -> O(log(n))
     SC -> O(1)
     */
     
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Base case:
        // If we reach a null node, there's nothing to process.
        if (root == null)
            return null;

        /**
         * Case 1:
         * If both p and q values are smaller than root.val,
         * then both nodes must lie in the LEFT subtree.
         *
         * So, the LCA must also be in the left subtree.
         * We recursively move left.
         */
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        /**
         * Case 2:
         * If both p and q values are greater than root.val,
         * then both nodes must lie in the RIGHT subtree.
         *
         * So, the LCA must also be in the right subtree.
         * We recursively move right.
         */
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        /**
         * Case 3:
         * Otherwise, p and q lie on DIFFERENT sides of this root
         * OR one of them equals the root.
         *
         * That means the current root is the first node
         * where their paths diverge — hence it is the LCA.
         */
        else {
            return root;
        }
    }
}

