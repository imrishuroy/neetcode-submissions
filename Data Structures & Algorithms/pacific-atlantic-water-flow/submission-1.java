class Solution {
    public int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // where can water to this ocean come from ?

        // if we start from Pacific ocean ( top row and left col )
        // and move uphill, to the cell with heigh >= current

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int col = 0; col < n; col++) { // from top
            dfs(0, col, heights, pacific);
            dfs(m - 1, col, heights, atlantic);
        }

        for (int row = 0; row < m; row++) {
            dfs(row, 0, heights, pacific);
            dfs(row, n - 1, heights, atlantic);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }

        return result;
    }

    private void dfs(int i, int j, int[][] heights, boolean[][] visited) {
        if (visited[i][j])
            return;

        visited[i][j] = true;

        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            if (newRow >= 0 && newRow < heights.length && newCol >= 0
                && newCol < heights[0].length) {
                if (heights[newRow][newCol] >= heights[i][j]) {
                    dfs(newRow, newCol, heights, visited);
                }
            }
        }
    }
}