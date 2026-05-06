/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder str = new StringBuilder();
        serializeHelper(root, str);

        return str.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder str) {
        if (node == null) {
            str.append("#").append(",");
            return;
        }

        str.append(node.val).append(",");
        serializeHelper(node.left, str);
        serializeHelper(node.right, str);
    }

    private int index;



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArray = data.split(",");
        System.out.println(Arrays.toString(strArray));
        // Deque<String> dq = new ArrayDeque<>(Arrays.asList(strArray));

        // Arrays.asList(strArray)); - O(1) operation
        // Deque<String> dq = new ArrayDeque<>(Arrays.asList(strArray)); - O(n) operation

        // return deserializeHelper(dq);

        index = 0;

        return deserializeHelper2(strArray);

    }

    private TreeNode deserializeHelper2(String[] strArray) {
        String str = strArray[index++];
        if (str.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = deserializeHelper2(strArray);

        root.right = deserializeHelper2(strArray);

        return root;
    }

    // private TreeNode deserializeHelper(Deque<String> dq) {
    //     String str = dq.poll();
    //     if (str.equals("#")) {
    //         return null;
    //     }

    //     TreeNode root = new TreeNode(Integer.parseInt(str));
    //     root.left = deserializeHelper(dq);
    //     root.right = deserializeHelper(dq);

    //     return root;
    // }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));