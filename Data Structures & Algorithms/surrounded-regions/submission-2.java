class Solution {
    public static int[][] directions = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };
    public void solve(char[][] board) {
        
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(i, 0, board, visited);
            dfs(i, n - 1, board, visited);
        }

        for (int j = 0; j < n; j++) {
            dfs(0, j, board, visited);
            dfs(m - 1, j, board, visited);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] board, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }

        if (visited[i][j] || board[i][j] == 'X') {
            return;
        } 

        visited[i][j] = true;

        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            dfs(newRow, newCol, board, visited);
        }
    }
}
