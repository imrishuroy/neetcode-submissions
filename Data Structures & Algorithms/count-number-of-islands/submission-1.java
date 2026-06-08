class Solution {
    public static int[][] directions = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(i, j, grid, visited);
                }
            }
        }

        return count;
    }

    private void dfs(int i, int j, char[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }

        if (visited[i][j] || grid[i][j] == '0') return;

        visited[i][j] = true;

        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            dfs(newRow, newCol, grid, visited);
        }
    }
}
