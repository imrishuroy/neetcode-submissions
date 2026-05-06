/**
 * Definition for a binary tree node.
 * Each node has a value and references to left and right children.
 */
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode() {}
//     TreeNode(int val) { this.val = val; }
//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

/**
 * PROBLEM:
 * Check whether a given binary tree is a valid Binary Search Tree (BST).
 *
 * BST RULE:
 * - All values in left subtree < current node value
 * - All values in right subtree > current node value
 * - This rule must hold for every node in the tree
 *
 * INTUITION:
 * Instead of only comparing a node with its immediate children,
 * we carry the allowed (min, max) range down from ancestors.
 * Each node must lie strictly within its allowed range.
 *
 * This technique is called:
 * "DFS with Range Propagation"
 */
class Solution {

    /**
     * Starts validation from the root.
     * Initially, root can take any value, so range is (-∞, +∞).
     */
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * dfs(node, low, high)
     *
     * Meaning:
     * "Check whether the subtree rooted at 'node' is a valid BST,
     * assuming all node values must lie between (low, high)."
     *
     * PARAMETERS:
     * node → current tree node we are validating
     * low  → minimum allowed value for this node (from ancestors)
     * high → maximum allowed value for this node (from ancestors)
     */
    private boolean dfs(TreeNode node, long low, long high) {

        // Base Case:
        // An empty subtree is always a valid BST
        if (node == null) return true;

        // Step 1: Check whether current node respects the allowed range.
        // If node.val is NOT strictly between (low, high),
        // it violates the BST property → return false.
        if (node.val <= low || node.val >= high) {
            return false;
        }

        // Step 2: Recursively validate left subtree
        // Left child must be smaller than current node,
        // so new upper bound becomes node.val
        boolean leftIsValid = dfs(node.left, low, node.val);

        // Step 3: Recursively validate right subtree
        // Right child must be greater than current node,
        // so new lower bound becomes node.val
        boolean rightIsValid = dfs(node.right, node.val, high);

        // Step 4: Current subtree is valid only if both sides are valid
        return leftIsValid && rightIsValid;
    }
}
