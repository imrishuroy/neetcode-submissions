class Solution {

    // ------------------------------------------------------------
    // MAIN FUNCTION
    // ------------------------------------------------------------
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        /*
         Intuition:
         preorder -> first element is root
         inorder  -> left of root = left subtree, right of root = right subtree
         */

        // Build hashmap: value -> index in inorder (for O(1) lookup)
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Build tree using full array ranges
        return buildSubtree(
            preorder, 0, preorder.length - 1,
            inorder,  0, inorder.length - 1,
            inorderIndexMap
        );
    }

    // ------------------------------------------------------------
    // RECURSIVE SUBTREE BUILDER (OPTIMIZED O(N))
    // ------------------------------------------------------------
    private TreeNode buildSubtree(int[] preorder, int preStart, int preEnd,
                                  int[] inorder,  int inStart,  int inEnd,
                                  Map<Integer, Integer> inorderIndexMap) {

        // Base case: no elements to form a tree
        if (preStart > preEnd) return null;

        // 1️⃣ Root is always first element in preorder range
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // 2️⃣ Find root index in inorder using HashMap
        int rootIndexInInorder = inorderIndexMap.get(rootValue);

        // 3️⃣ Number of nodes in left subtree
        int leftSubtreeSize = rootIndexInInorder - inStart;

        // 4️⃣ Recursively build left subtree
        root.left = buildSubtree(
            preorder,
            preStart + 1,                       // skip root
            preStart + leftSubtreeSize,         // left subtree length
            inorder,
            inStart,
            rootIndexInInorder - 1,
            inorderIndexMap
        );

        // 5️⃣ Recursively build right subtree
        root.right = buildSubtree(
            preorder,
            preStart + leftSubtreeSize + 1,     // start after left subtree
            preEnd,
            inorder,
            rootIndexInInorder + 1,
            inEnd,
            inorderIndexMap
        );

        return root;
    }

    // ------------------------------------------------------------
    // BRUTE FORCE VERSION (for learning reference)
    // Time: O(N^2)
    // ------------------------------------------------------------
    private TreeNode buildSubtreeBrute(int[] preorder, int preStart, int preEnd,
                                       int[] inorder,  int inStart,  int inEnd) {

        if (preStart > preEnd) return null;

        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // Linear scan to find root in inorder
        int rootIndexInInorder = inStart;
        while (inorder[rootIndexInInorder] != rootValue) {
            rootIndexInInorder++;
        }

        int leftSubtreeSize = rootIndexInInorder - inStart;

        root.left = buildSubtreeBrute(
            preorder,
            preStart + 1,
            preStart + leftSubtreeSize,
            inorder,
            inStart,
            rootIndexInInorder - 1
        );

        root.right = buildSubtreeBrute(
            preorder,
            preStart + leftSubtreeSize + 1,
            preEnd,
            inorder,
            rootIndexInInorder + 1,
            inEnd
        );

        return root;
    }
}
