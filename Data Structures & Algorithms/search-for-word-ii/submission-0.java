class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, root, result);
            }
        }

        return result;
    }

    private void dfs(int i, int j, char[][] board, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        if (board[i][j] == '#') {
            return;
        }

        int index = board[i][j] - 'a';

        if (node.children[index] == null) {
            return;
        }

        node = node.children[index];

        if (node.word != null) {
            result.add(node.word);
            node.word = null; // prevent duplicates
        }
        char original = board[i][j];

        board[i][j] = '#';

        // explore 4 directions
        for (int[] dir : directions) {
            int newR = dir[0] + i;
            int newC = dir[1] + j;

            dfs(newR, newC, board, node, result);
        }

        board[i][j] = original;
    }
}
