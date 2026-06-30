class Solution {
    public void islandsAndTreasure(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;

        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                int r = node[0];
                int c = node[1];
                int dist = node[2];

                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];
                    int newDist = dist + 1;

                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                    && !visited[newRow][newCol] && grid[newRow][newCol] == Integer.MAX_VALUE) {
                        grid[newRow][newCol] = newDist;
                        queue.offer(new int[]{newRow, newCol, newDist});
                    }

                }
            }
        }
    }
}
