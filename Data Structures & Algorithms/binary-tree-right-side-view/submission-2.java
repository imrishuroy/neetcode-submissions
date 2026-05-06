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
            /// In BFS we explore the tree by level
            /// If we look at each level from left to right, last node
            /// we encounter at at level is the one visible from the 
            /// right side
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
}
