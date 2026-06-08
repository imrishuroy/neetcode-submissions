class Solution {
    public static int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxArea = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(i, j, grid, visited));
                }
            }
        }

        return maxArea;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }

        if (visited[i][j] || grid[i][j] == 0) {
            return 0;
        }

        visited[i][j] = true;

        int count = 1;

        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            count += dfs(newRow, newCol, grid, visited);
        }

        return count;
    }
}
