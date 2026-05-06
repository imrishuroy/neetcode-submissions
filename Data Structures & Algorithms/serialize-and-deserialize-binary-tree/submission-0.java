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

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder strB = new StringBuilder();

        serializeHelper(root, strB);
        return strB.toString();
        
    }

    private void serializeHelper(TreeNode node, StringBuilder strB) {
        if (node == null) {
            strB.append("#").append(",");
            return;
        }

        strB.append(node.val).append(",");
        serializeHelper(node.left, strB);
        serializeHelper(node.right, strB);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        String[] strList = data.split(",");
        Deque<String> dq = new ArrayDeque<>(Arrays.asList(strList));

        System.out.println(dq);

        return deserializeHelper(dq);
    }

    private TreeNode deserializeHelper(Deque dq) {
        String str = dq.poll().toString();
        if (str.equals("#")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(str));

        node.left = deserializeHelper(dq);
        node.right = deserializeHelper(dq);

        return node;
    }
}
