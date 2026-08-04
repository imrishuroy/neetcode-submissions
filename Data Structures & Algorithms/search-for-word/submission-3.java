class Solution {
    int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backtrack(i, j, 0, board, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtrack(int row, int col, int k, char[][] board, String word) {
        if (k == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        if (board[row][col] != word.charAt(k)) {
            return false;
        }

        char w = board[row][col];
        board[row][col] = '#';

        for (int[] dir : directions) {
            int newR = row + dir[0];
            int newC = col + dir[1];

            if (backtrack(newR, newC, k + 1, board, word)) {
                return true;
            }
        }

        board[row][col] = w;
        return false;
    }
}
