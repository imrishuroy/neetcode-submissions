class Solution {

    private final int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };


    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // start DFS only if first character matches
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(i, j, 0, word, board, visited)) return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int row, int col, int index, String word, char[][] board, boolean[][] visited) {
        if (index == word.length()) return true;

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) return false;

        if (board[row][col] != word.charAt(index)) return false; 

        visited[row][col] = true;

        for (int[] dir : directions) {
            int newR = row + dir[0];
            int newC = col + dir[1];

            if (dfs(newR, newC, index + 1, word, board, visited)) return true;
        }

        visited[row][col] = false;
        return false;
    }
}
