class Solution {
    public int orangesRotting(int[][] grid) {
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) { // start from rotton fruits
                    queue.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0)
            return 0;

        int minute = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isRotten = false;

            for (int i = 0; i < size; i++) { // rot the fruits in bataches
                int[] node = queue.poll();

                for (int[] dir : directions) {
                    int nRow = node[0] + dir[0];
                    int nCol = node[1] + dir[1];

                    if (nRow >= 0 && nRow < m && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1) {
                        grid[nRow][nCol] = 2;
                        fresh--;
                        isRotten = true;
                        queue.offer(new int[] {nRow, nCol});
                    }
                }
            }

            if (isRotten) {
                minute++;
            }
        }

        return fresh == 0 ? minute : -1;
    }
}
