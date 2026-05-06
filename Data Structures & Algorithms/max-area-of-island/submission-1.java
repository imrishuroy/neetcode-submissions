class Solution {
    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int[][] dirs = { {1, 0 }, {-1, 0}, {0, 1}, {0, -1} };

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, dfs(i, j, grid, visited, dirs));
                }
            }
        }


        return maxArea;

    }

    private int dfs(int r, int c, int[][] grid, boolean[][] visited, int[][] dirs) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) return 0;

        if (visited[r][c] || grid[r][c] == 0) return 0;

        visited[r][c] = true;

        int area = 1;

        for (int[] dir : dirs) {
            area += dfs(r + dir[0], c + dir[1], grid, visited, dirs);
        }
        
        return area;

    }


}
