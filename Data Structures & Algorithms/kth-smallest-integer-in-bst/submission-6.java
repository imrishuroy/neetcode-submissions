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
     * Intuition:
     * In a Binary Search Tree (BST), an inorder traversal (Left → Root → Right)
     * visits nodes in sorted (ascending) order.
     * 
     * So, if we perform inorder traversal and store values,
     * the k-th element in this sorted list is the k-th smallest element in the BST.
     *
     * Approach:
     * 1. Perform inorder DFS traversal.
     * 2. Add each visited node's value to a list.
     * 3. Stop traversal early once we have collected more than k elements.
     * 4. Return the (k-1)th index from the list (because list is 0-based).
     *
     * Why this works:
     * BST property guarantees inorder traversal outputs sorted values.
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        
        // Perform inorder traversal to collect sorted values
        dfs(root, list, k);
        
        // k-th smallest is at index k-1
        return list.get(k - 1);
    }

    /**
     * DFS helper for inorder traversal.
     *
     * What we are doing:
     * - Traverse left subtree first (smaller values).
     * - Then visit current node and add its value to list.
     * - Then traverse right subtree (larger values).
     *
     * Why early stopping:
     * Once list size exceeds k, we already have enough elements,
     * so we stop further recursion to save time.
     */
    private void dfs(TreeNode node, List<Integer> list, int k) {
        // Base case: if node is null, just return
        if (node == null) return;

        // Step 1: Visit left subtree (all smaller values)
        dfs(node.left, list, k);

        // Step 2: Add current node value to list
        list.add(node.val);

        // Step 3: If we already collected more than k elements,
        // no need to continue traversal
        if (list.size() > k) return;

        // Step 4: Visit right subtree (larger values)
        dfs(node.right, list, k);
    }
}
