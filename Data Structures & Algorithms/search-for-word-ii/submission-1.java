class Solution {

    /*
     * ----------------------------------------------------
     * Intuition
     * ----------------------------------------------------
     *
     * We are given:
     * 1. A 2D board containing lowercase English letters.
     * 2. A list of words.
     *
     * Our goal is to find every word that can be formed on the board.
     *
     * Rules:
     * - We can move only in 4 directions
     *   (Up, Down, Left, Right).
     * - We cannot reuse the same cell while forming one word.
     *
     * ----------------------------------------------------
     * Brute Force
     * ----------------------------------------------------
     *
     * A simple approach would be:
     *
     * For every word:
     *      Start DFS from every cell.
     *
     * Example:
     *
     * Words = ["cat", "car", "dog"]
     *
     * We search for "cat".
     * Then again search for "car".
     * Then again search for "dog".
     *
     * Notice that many searches repeat exactly the same work.
     *
     * This becomes very slow when there are many words.
     *
     * ----------------------------------------------------
     * Better Idea
     * ----------------------------------------------------
     *
     * Instead of searching one word at a time,
     * we store ALL words inside a Trie.
     *
     * A Trie stores words by sharing common prefixes.
     *
     * Example:
     *
     * words = ["cat", "car", "can"]
     *
     *              root
     *                |
     *                c
     *                |
     *                a
     *             /  |  \
     *            t   r   n
     *
     * Notice that "ca" is stored only once.
     *
     * Now while exploring the board,
     * we also move through the Trie.
     *
     * The moment the current board character
     * is not present in the Trie,
     * we immediately stop exploring.
     *
     * This is called Trie Pruning.
     *
     * Trie pruning avoids exploring useless paths
     * and makes the solution much faster.
     *
     * ----------------------------------------------------
     * Approach
     * ----------------------------------------------------
     *
     * Step 1:
     * Insert every word into the Trie.
     *
     * Step 2:
     * Start DFS from every cell because
     * any cell can be the starting character
     * of a word.
     *
     * During DFS:
     *
     * 1. Check boundary conditions.
     *
     * 2. Ignore already visited cells.
     *
     * 3. Check whether current character
     *    exists inside the Trie.
     *
     * 4. Move to the corresponding Trie node.
     *
     * 5. If this Trie node stores a complete word,
     *    add it to the answer.
     *
     * 6. Mark current cell as visited.
     *
     * 7. Explore all four directions.
     *
     * 8. Restore the original character
     *    (Backtracking).
     *
     * ----------------------------------------------------
     * Time Complexity
     * ----------------------------------------------------
     *
     * Let:
     *
     * M = rows
     * N = columns
     * W = number of words
     * L = maximum word length
     *
     * Building Trie:
     * O(W * L)
     *
     * DFS:
     * Worst Case:
     * O(M * N * 4 * 3^(L-1))
     *
     * The first move has 4 possible directions.
     * Every next move has only 3 directions because
     * we cannot immediately go back to the previous cell.
     *
     * In practice,
     * Trie pruning removes most unnecessary searches,
     * making the solution much faster.
     *
     * ----------------------------------------------------
     * Space Complexity
     * ----------------------------------------------------
     *
     * Trie:
     * O(W * L)
     *
     * DFS recursion stack:
     * O(L)
     *
     * Total:
     * O(W * L)
     */

    class TrieNode {

        /*
         * Every node has 26 children,
         * one for each lowercase English letter.
         *
         * children[0] -> 'a'
         * children[1] -> 'b'
         * ...
         * children[25] -> 'z'
         */
        TrieNode[] children = new TrieNode[26];

        /*
         * If this node represents the end of a word,
         * store the complete word.
         *
         * Example:
         *
         * Word = "apple"
         *
         * Only the node representing 'e'
         * stores "apple".
         *
         * All previous nodes contain null.
         */
        String word = null;
    }

    /*
     * Directions used for DFS.
     *
     * Right
     * Down
     * Up
     * Left
     */
    int[][] directions = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public List<String> findWords(char[][] board, String[] words) {

        /*
         * Create Trie root.
         */
        TrieNode root = new TrieNode();

        /*
         * ------------------------------------------------
         * Build the Trie
         * ------------------------------------------------
         *
         * Insert every word character by character.
         *
         * Example:
         *
         * "cat"
         *
         * root
         *   |
         *   c
         *   |
         *   a
         *   |
         *   t
         *
         * The last node stores "cat".
         */
        for (String word : words) {

            TrieNode node = root;

            for (char ch : word.toCharArray()) {

                int index = ch - 'a';

                /*
                 * Create the child node
                 * if it does not already exist.
                 */
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                /*
                 * Move to the next Trie node.
                 */
                node = node.children[index];
            }

            /*
             * Store the complete word
             * at the last character.
             */
            node.word = word;
        }

        /*
         * Stores all words found on the board.
         */
        List<String> result = new ArrayList<>();

        /*
         * Every board cell can be
         * the starting point of a word.
         *
         * Therefore start DFS
         * from every cell.
         */
        for (int row = 0; row < board.length; row++) {

            for (int col = 0; col < board[0].length; col++) {

                dfs(row, col, board, root, result);
            }
        }

        return result;
    }

    private void dfs(int row,
                     int col,
                     char[][] board,
                     TrieNode node,
                     List<String> result) {

        /*
         * -------------------------------
         * Step 1
         * Boundary Check
         * -------------------------------
         *
         * If we move outside the board,
         * simply stop.
         */
        if (row < 0 ||
                row >= board.length ||
                col < 0 ||
                col >= board[0].length) {

            return;
        }

        /*
         * -------------------------------
         * Step 2
         * Visited Check
         * -------------------------------
         *
         * '#' means this cell is already
         * being used in the current path.
         *
         * A cell cannot be reused while
         * forming one word.
         */
        if (board[row][col] == '#') {
            return;
        }

        /*
         * Current board character.
         */
        char currentChar = board[row][col];

        /*
         * Convert character into Trie index.
         *
         * Example:
         *
         * 'a' -> 0
         * 'b' -> 1
         * 'c' -> 2
         */
        int index = currentChar - 'a';

        /*
         * -------------------------------
         * Step 3
         * Trie Pruning
         * -------------------------------
         *
         * If the current character
         * does not exist in the Trie,
         * then no word starts with this path.
         *
         * Stop exploring immediately.
         *
         * This pruning makes the solution fast.
         */
        if (node.children[index] == null) {
            return;
        }

        /*
         * Move to the child Trie node.
         *
         * We have successfully matched
         * the current character.
         */
        node = node.children[index];

        /*
         * -------------------------------
         * Step 4
         * Found a Word
         * -------------------------------
         *
         * If this Trie node stores a word,
         * we have found one valid answer.
         */
        if (node.word != null) {

            result.add(node.word);

            /*
             * Prevent duplicates.
             *
             * Different DFS paths may reach
             * the same Trie node.
             *
             * After adding the word once,
             * set it to null.
             */
            node.word = null;
        }

        /*
         * -------------------------------
         * Step 5
         * Mark Visited
         * -------------------------------
         *
         * Save the original character
         * because we'll restore it later.
         */
        board[row][col] = '#';

        /*
         * -------------------------------
         * Step 6
         * Explore Neighbours
         * -------------------------------
         *
         * Continue DFS in all four directions.
         */
        for (int[] dir : directions) {

            dfs(
                    row + dir[0],
                    col + dir[1],
                    board,
                    node,
                    result
            );
        }

        /*
         * -------------------------------
         * Step 7
         * Backtracking
         * -------------------------------
         *
         * Restore the original character.
         *
         * Why?
         *
         * Because other DFS calls may need
         * to use this cell while searching
         * for different words.
         */
        board[row][col] = currentChar;
    }
}