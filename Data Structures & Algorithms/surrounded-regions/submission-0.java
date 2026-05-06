class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int[][] dirs = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        // top and bottom rows
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == 'O') {
                dfs(0, c, board, visited, dirs);
            }

            if (board[rows - 1][c] == 'O') {
                dfs(rows - 1, c, board, visited, dirs);
            }
        }


        // left and right colums
        for (int r = 0; r < rows; r++) {
            if (board[r][0] == 'O') {
                dfs(r, 0, board, visited, dirs);
            }

            if (board[r][cols - 1] == 'O') {
                dfs(r, cols - 1, board, visited, dirs);
            }
        }

        for (int i = 1; i < rows - 1 ; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        
    }


    private void dfs(int r, int c, char[][] board, boolean[][] visited, int[][] dirs) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) return;

        if (visited[r][c] || board[r][c] == 'X') return;

        if (board[r][c] == 'O') {
            visited[r][c] = true;
        }

        for (int[] dir: dirs) {
            dfs(r + dir[0], c + dir[1], board, visited, dirs);
        }

    }
}
