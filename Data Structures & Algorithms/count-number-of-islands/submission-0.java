class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int count = 0;

        int[][] dirs = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '0') {
                    count++;
                    dfs(i, j, grid, dirs);
                }
            }
        }

        return count;
    }

    private void dfs(int r, int c, char[][] grid, int[][] dirs) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) return;
        if (grid[r][c] == '0') return;

        grid[r][c] = '0';

        for (int[] dir: dirs) {
            dfs(r + dir[0], c + dir[1], grid, dirs);
        }
    }
}
