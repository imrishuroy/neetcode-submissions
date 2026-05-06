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
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        dfsWithPQ(root, maxHeap, k);

        return maxHeap.peek();

        // so we traverse the tree in-order, and put all the nodes values in a list, we will create a sorted array
        // List<Integer> list = new ArrayList<>();

        // dfs(root, list, k);
        // return list.get(k - 1);

    }

    // in order
    private void dfs(TreeNode node, List<Integer> list, int k) {
        if (node == null) return;

        dfs(node.left, list, k);

        list.add(node.val);

        // return early if the size of list exceeds k
        if (list.size() > k) {
            return;
        }


        dfs(node.right, list, k);
    }



    private void dfsWithPQ(TreeNode node, PriorityQueue<Integer> maxHeap, int k) {
        if (node == null) return;

        dfsWithPQ(node.left, maxHeap, k);
        
        maxHeap.add(node.val);

        if (maxHeap.size() > k) {
            maxHeap.poll();
        }

        
        dfsWithPQ(node.right, maxHeap, k);
    }
}
