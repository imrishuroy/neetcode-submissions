class Solution {

    /*
     * Intuition:
     * ----------
     * A rotten orange can rot its adjacent fresh oranges (up, down, left, right)
     * in exactly 1 minute.
     *
     * Instead of simulating minute-by-minute for each orange separately,
     * we can use Multi-Source BFS:
     *
     * - Treat every initially rotten orange as a starting point.
     * - All rotten oranges spread simultaneously.
     * - BFS naturally processes nodes level by level.
     * - One BFS level = one minute of time.
     *
     * This makes BFS a perfect fit because rotting spreads outward in waves.
     *
     *
     * Approach:
     * ---------
     * 1. Traverse the grid once:
     *      - Add all rotten oranges (2) into the queue.
     *      - Count total fresh oranges (1).
     *
     * 2. If there are no fresh oranges, return 0 immediately.
     *
     * 3. Perform BFS:
     *      - Each BFS level represents one minute.
     *      - Process all currently rotten oranges.
     *      - Rot all adjacent fresh oranges.
     *      - Add newly rotten oranges into the queue.
     *
     * 4. After each level:
     *      - If at least one fresh orange became rotten,
     *        increment the minute counter.
     *
     * 5. At the end:
     *      - If all fresh oranges became rotten, return minutes.
     *      - Otherwise some oranges were unreachable, return -1.
     *
     *
     * Why Multi-Source BFS?
     * ---------------------
     * Since all rotten oranges spread at the same time,
     * starting BFS from every rotten orange simultaneously
     * correctly models the real-world process.
     *
     *
     * Time Complexity:
     * ----------------
     * O(m * n)
     *
     * Every cell is visited at most once.
     *
     *
     * Space Complexity:
     * -----------------
     * O(m * n)
     *
     * In the worst case all cells may be stored in the queue.
     */
    public int orangesRotting(int[][] grid) {

        // Directions for exploring neighbors:
        // right, down, up, left
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        int m = grid.length;
        int n = grid[0].length;

        // Queue used for BFS.
        // Each element stores {row, col}.
        Queue<int[]> queue = new LinkedList<>();

        // Count of fresh oranges remaining.
        int fresh = 0;

        /*
         * Step 1:
         * Collect all initially rotten oranges.
         * These become our BFS starting points.
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 2) {
                    // Multi-source BFS starts from all rotten oranges.
                    queue.offer(new int[]{i, j});

                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // No fresh oranges exist, so no time is needed.
        if (fresh == 0) {
            return 0;
        }

        int minute = 0;

        /*
         * BFS Traversal
         *
         * Each iteration of the while loop processes
         * one complete BFS level (one minute).
         */
        while (!queue.isEmpty()) {

            int size = queue.size();

            /*
             * Tracks whether at least one fresh orange
             * became rotten during this minute.
             */
            boolean isRotten = false;

            /*
             * Process all oranges that are rotten
             * at the current minute.
             */
            for (int i = 0; i < size; i++) {

                int[] node = queue.poll();

                int row = node[0];
                int col = node[1];

                // Explore all 4 neighboring cells.
                for (int[] dir : directions) {

                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    /*
                     * Valid neighbor?
                     * Fresh orange?
                     *
                     * If yes:
                     * - Rot it
                     * - Reduce fresh count
                     * - Add it to queue
                     *
                     * It will spread rot in the next minute.
                     */
                    if (newRow >= 0 &&
                        newRow < m &&
                        newCol >= 0 &&
                        newCol < n &&
                        grid[newRow][newCol] == 1) {

                        grid[newRow][newCol] = 2; // rot this

                        fresh--;

                        isRotten = true;

                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            /*
             * If at least one orange became rotten during
             * this BFS level, one minute has passed.
             */
            if (isRotten) {
                minute++;
            }
        }

        /*
         * If fresh == 0:
         * All oranges became rotten successfully.
         *
         * Otherwise:
         * Some fresh oranges were unreachable.
         */
        return fresh == 0 ? minute : -1;
    }
}