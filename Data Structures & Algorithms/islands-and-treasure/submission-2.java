class Solution {
    // BFS == nearest
    public void islandsAndTreasure(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] directions = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };

        Queue<int[]> queue = new LinkedList<>();
        // here we don't need visited

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) { // expand from treasure
                    queue.offer(new int[]{i, j, 0}); // 0 is for the distance
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0];
            int c = node[1];
            int dist = node[2];

            for (int[] dir : directions) {
                int newR = dir[0] + r;
                int newC = dir[1] + c;
                int newDist = dist + 1;

                if (newR >= 0 && newR < m && newC >= 0 && newC < n 
                && grid[newR][newC] == Integer.MAX_VALUE) {
                    grid[newR][newC] = newDist;
                    queue.offer(new int[]{newR, newC, newDist});
                }
            }
        }
    }
}
