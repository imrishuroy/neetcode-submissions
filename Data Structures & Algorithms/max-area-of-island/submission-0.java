class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(grid, visited, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;

    }

    private int dfs(int[][] grid, boolean[][] visited, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (r < 0 || c < 0 || r >= rows || c >= cols) return 0;

        if (visited[r][c]) return 0;
        if (grid[r][c] == 0) return 0; // skip, if it is water cell

        visited[r][c] = true;

        int count = 1;
        
        // move up
        count += dfs(grid, visited, r - 1, c);

        // move down
        count += dfs(grid, visited, r + 1, c);

        // move left 
        count += dfs(grid, visited, r, c - 1);

        // move right
        count += dfs(grid, visited, r, c + 1);

        return count;
    }
}
