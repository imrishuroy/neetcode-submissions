class Solution {
    private static final int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int minutes = 0;
        int freshOranges = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if(grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }


        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            minutes++;

            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();

                for (int[] dir : directions) {
                    int newX = node[0] + dir[0];
                    int newY = node[1] + dir[1];

                    if (newX >= 0 && newX < rows &&
                        newY >= 0 && newY < cols &&
                        grid[newX][newY] == 1
                    ) { 
                        grid[newX][newY] = 2;
                        freshOranges--;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }

        return freshOranges > 0 ? -1 : minutes;

    }
}
