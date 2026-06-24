class Solution {
    public void islandsAndTreasure(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        Queue<int[]> queue = new LinkedList<>();
        // multi source bfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    queue.offer(new int[] {i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            int dist = node[2];

            // explore the neighbours
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (grid[newX][newY] == Integer.MAX_VALUE) {
                        grid[newX][newY] = dist + 1;
                        queue.offer(new int[] {newX, newY, dist + 1});
                    }
                }
            }
        }
    }
}
