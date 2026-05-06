class Solution {

    /*

    INTUITION:

    preorder  = Root → Left → Right
    inorder   = Left → Root → Right

    Therefore:
    - First element in preorder = Root of current subtree
    - In inorder:
        Elements LEFT of root  → Left Subtree
        Elements RIGHT of root → Right Subtree

    We repeat this recursively to rebuild the entire tree.
    ------------------------------------------------------------

    QUICK REVISION CHEAT SHEET:

        Root = preorder[preStart]

        Find rootIndex in inorder

        leftSize = rootIndex - inStart

        Left Subtree:
          preorder -> [preStart + 1 ... preStart + leftSize]
          inorder  -> [inStart ... rootIndex - 1]

        Right Subtree:
          preorder -> [preStart + leftSize + 1 ... preEnd]
          inorder  -> [rootIndex + 1 ... inEnd]

    ------------------------------------------------------------
    TIME COMPLEXITY:
      O(N) using HashMap for inorder index lookup

    SPACE COMPLEXITY:
      O(N) for HashMap + recursion stack
    ============================================================
    */

    // Stores value -> index mapping for inorder traversal
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    // ------------------------------------------------------------
    // MAIN FUNCTION
    // ------------------------------------------------------------
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // Build hashmap for quick inorder index lookup
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Start building tree using full array ranges
        return buildSubtree(
            preorder, 0, preorder.length - 1,
            inorder,  0, inorder.length - 1
        );
    }

    // ------------------------------------------------------------
    // RECURSIVE FUNCTION TO BUILD SUBTREE
    //
    // preorder[preStart ... preEnd] → Current preorder range
    // inorder [inStart  ... inEnd]  → Current inorder range
    // ------------------------------------------------------------
    private TreeNode buildSubtree(int[] preorder, int preStart, int preEnd,
                                  int[] inorder,  int inStart,  int inEnd) {

        // BASE CASE:
        // If range is invalid, no subtree exists
        if (preStart > preEnd) return null;

        // STEP 1:
        // Pick root from preorder (first element in current range)
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // STEP 2:
        // Find root position in inorder using HashMap
        int rootIndexInInorder = inorderIndexMap.get(rootValue);

        /*
            Now inorder is divided as:

            inorder[inStart ... rootIndexInInorder - 1] → Left Subtree
            inorder[rootIndexInInorder]                 → Root
            inorder[rootIndexInInorder + 1 ... inEnd]   → Right Subtree
        */

        // STEP 3:
        // Count nodes in left subtree
        int leftSubtreeSize = rootIndexInInorder - inStart;

        /*
            preorder is divided as:

            preorder[preStart]                           → Root
            preorder[preStart + 1 ... preStart + leftSubtreeSize]
                                                       → Left Subtree
            preorder[preStart + leftSubtreeSize + 1 ... preEnd]
                                                       → Right Subtree
        */

        // STEP 4:
        // Recursively build LEFT subtree
        root.left = buildSubtree(
            preorder,
            preStart + 1,                       // skip root
            preStart + leftSubtreeSize,         // end of left subtree
            inorder,
            inStart,                            // start of left inorder
            rootIndexInInorder - 1              // end of left inorder
        );

        // STEP 5:
        // Recursively build RIGHT subtree
        root.right = buildSubtree(
            preorder,
            preStart + leftSubtreeSize + 1,     // start of right subtree
            preEnd,                             // end of preorder
            inorder,
            rootIndexInInorder + 1,             // start of right inorder
            inEnd                               // end of right inorder
        );

        // STEP 6:
        // Return constructed root node
        return root;
    }
}
