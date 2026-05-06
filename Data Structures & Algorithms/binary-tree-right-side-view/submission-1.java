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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode rightSide = null;
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode top = queue.poll();

                if (top != null) {
                    rightSide = top;
                    queue.offer(top.left);
                    queue.offer(top.right);
                }
            }

            if (rightSide != null) {
                result.add(rightSide.val);
            }
            
        }


        return result;
    }

    // private void dfs(TreeNode root, List<Integer> result) {
    //     if (root == null) return;

    //     result.add(root.val);

    //     if (root.right != null) {
    //         dfs(root.right, result);
    //     }
    // }
}
