class Solution {

    static private final int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public void islandsAndTreasure(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return;

        Queue<int[]> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) { // contains tresure
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            int x = node[0];
            int y = node[1];
            int dist = node[2];

            for (int [] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < rows &&
                    newY >= 0 && newY < cols &&
                    grid[newX][newY] == Integer.MAX_VALUE
                ) {
                    grid[newX][newY] = dist + 1;
                    queue.offer(new int[]{newX, newY, dist + 1});
                }
            }
        }

    }
}
