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
    // so if we do in order traversal for BST, then we will get numbers in accending order
    public int kthSmallest(TreeNode root, int k) {
        int[] arr = new int[2];
        arr[1] = k;
        dfs(root, arr);

        return arr[0];
    }

    private void dfs(TreeNode node, int[] arr) {
        if (node == null) {
            return;
        }

        dfs(node.left, arr);

        // if (arr[1] > 0) {
            arr[1] = arr[1] - 1;
            if (arr[1] == 0) {
                arr[0] = node.val;
                return;
            }
        // }

        dfs(node.right, arr);
    }
}
