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
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // all the roots are in preorder array, because roots is evaluated first in preorder
        // find that root value in the in order array, all the elements to its left is the left sub tree, 
        // and all the elements to the right is right subtree

        /*

        1. if either array is empty, return null (base case)
        2. create a root node with first element of preorder
        3. Find the index of the root in in order ( call it mid )
        4. Recursively build the left subtree using preorder[1: mid + 1] and inorder[0: mid]
        5. Recursively build the right subtree using proorder[mid + 1: ] and inorder[mid + 1: ]

        */

        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        int mid = -1;

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                mid = i;
                break;
            }
        }

        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, mid + 1);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, mid);

        root.left = buildTree(leftPreorder, leftInorder);

        int[] rightPreorder = Arrays.copyOfRange(preorder, mid + 1, preorder.length);

        int[] rightInorder = Arrays.copyOfRange(inorder, mid + 1, inorder.length);

        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }
}
