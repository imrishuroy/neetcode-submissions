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

        if (root == null) {
            return "";
        }

        StringBuilder str = new StringBuilder();

        dfs(root, str);

        System.out.println(str.toString());

        return str.toString();

        
    }

    private void dfs(TreeNode node, StringBuilder str) {

        // if (node == null) {
        //     return;
        // }

        str.append(node.val + " ");

        if (node.left != null) {
            dfs(node.left, str);
        } else {
            str.append("null" + " ");
        }

        if (node.right != null) {
            dfs(node.right, str);
        } else {
            str.append("null" + " ");
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] tokens = data.split(" ");

        int[] index = {0};

        return buildTree(tokens, index);
    }

    private TreeNode buildTree(String[] tokens, int[] index) {
        if (index[0] >= tokens.length) return null;
        String token = tokens[index[0]++];

        if (token.equals("null")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(token));
        root.left = buildTree(tokens, index);
        root.right = buildTree(tokens, index);

        return root;

    }
}
