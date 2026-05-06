/**
 * Intuition:
 * ----------
 * The diameter of a binary tree is the length (number of edges)
 * of the longest path between any two nodes.
 *
 * Key observation:
 * - The longest path may or may NOT pass through the root.
 * - But for any node, the longest path passing THROUGH that node
 *   is: height(left subtree) + height(right subtree)
 *
 * So, at every node:
 * - We compute the height of its left and right subtrees
 * - We check if connecting those two paths gives a better diameter
 *
 * This means:
 * - DFS should return HEIGHT (for parent usage)
 * - DFS should UPDATE a global DIAMETER (side effect)
 *
 * This is a classic postorder DFS pattern:
 * - Left -> Right -> Node
 */
class Solution {

    // Stores the maximum diameter found so far across all nodes
    int result = 0;

    /**
     * Approach:
     * ---------
     * We traverse the tree using DFS.
     * At each node:
     *   1. Get height of left subtree
     *   2. Get height of right subtree
     *   3. Update diameter using left + right
     *   4. Return height of current node to parent
     *
     * Time Complexity: O(n)
     * Space Complexity: O(h) where h is height of tree
     */
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);          // start postorder DFS
        return result;      // final diameter in terms of edges
    }

    /**
     * dfs(node) returns:
     * -------------------
     * The HEIGHT of the subtree rooted at this node.
     *
     * Why height?
     * - Parent nodes need height to compute their own diameter
     *
     * Why not diameter?
     * - Diameter is a global property, not tied to one node
     */
    private int dfs(TreeNode root) {

        // Base case:
        // A null node contributes height = 0
        if (root == null) {
            return 0;
        }

        // Recursively compute height of left subtree
        int leftHeight = dfs(root.left);

        // Recursively compute height of right subtree
        int rightHeight = dfs(root.right);

        /**
         * At this node, the longest path passing THROUGH it
         * would be:
         *
         *   left subtree height + right subtree height
         *
         * This counts edges, not nodes.
         */
        int diameterThroughCurrent = leftHeight + rightHeight;

        // Update global diameter if this path is longer
        result = Math.max(result, diameterThroughCurrent);

        /**
         * Return height of current node to parent:
         *
         * Height = 1 (current node) + max(left, right)
         *
         * Parent only needs the longest downward path.
         */
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
