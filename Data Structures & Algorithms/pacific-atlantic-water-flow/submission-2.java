/**
 * 417. Pacific Atlantic Water Flow
 *
 * Intuition:
 * ----------
 * Water can flow from a cell to its neighboring cell if:
 *
 *     neighborHeight <= currentHeight
 *
 * A brute force approach would be:
 * - Start from every cell.
 * - Check if water can reach both oceans.
 *
 * This would be very expensive because we would perform a DFS/BFS
 * from every cell.
 *
 * Instead, we reverse our thinking:
 *
 * Rather than asking:
 *     "Can this cell reach the ocean?"
 *
 * We ask:
 *     "Which cells can reach this ocean?"
 *
 * Since we are traversing backwards from the ocean,
 * we reverse the flow condition.
 *
 * Normal water flow:
 *     high -> low
 *
 * Reverse DFS flow:
 *     low -> high
 *
 * Therefore, during DFS we can move to a neighbor only if:
 *
 *     neighborHeight >= currentHeight
 *
 * because water could have flowed from that higher/equal cell
 * down to the current cell.
 *
 * Approach:
 * ---------
 * 1. Create two visited matrices:
 *
 *      pacific[r][c]
 *      atlantic[r][c]
 *
 * 2. Start DFS from all Pacific border cells:
 *      - Top row
 *      - Left column
 *
 *    Mark every reachable cell in pacific[][]
 *
 * 3. Start DFS from all Atlantic border cells:
 *      - Bottom row
 *      - Right column
 *
 *    Mark every reachable cell in atlantic[][]
 *
 * 4. Any cell that is reachable from BOTH DFS traversals
 *    can flow to both oceans.
 *
 *      pacific[r][c] && atlantic[r][c]
 *
 * Time Complexity:
 * ----------------
 * DFS for Pacific  -> O(m * n)
 * DFS for Atlantic -> O(m * n)
 *
 * Each cell is visited at most once in each DFS traversal.
 *
 * Total:
 *      O(m * n)
 *
 * Space Complexity:
 * -----------------
 * Pacific visited matrix  -> O(m * n)
 * Atlantic visited matrix -> O(m * n)
 * Recursion stack         -> O(m * n) worst case
 *
 * Total:
 *      O(m * n)
 */
class Solution {

    // Four possible directions:
    // right, down, left, up
    private final int[][] directions = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        // Cells reachable from Pacific Ocean
        boolean[][] pacific = new boolean[m][n];

        // Cells reachable from Atlantic Ocean
        boolean[][] atlantic = new boolean[m][n];

        /*
         * Pacific touches:
         * - Top row
         *
         * Atlantic touches:
         * - Bottom row
         */
        for (int col = 0; col < n; col++) {
            dfs(0, col, heights, pacific);
            dfs(m - 1, col, heights, atlantic);
        }

        /*
         * Pacific touches:
         * - Left column
         *
         * Atlantic touches:
         * - Right column
         */
        for (int row = 0; row < m; row++) {
            dfs(row, 0, heights, pacific);
            dfs(row, n - 1, heights, atlantic);
        }

        List<List<Integer>> result = new ArrayList<>();

        /*
         * A cell can flow to BOTH oceans
         * if it was reached during both DFS traversals.
         */
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {

                if (pacific[row][col] && atlantic[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }

        return result;
    }

    /**
     * DFS starting from an ocean border.
     *
     * We move only to cells with height >= current cell
     * because we are traversing in the reverse direction
     * of water flow.
     */
    private void dfs(int row, int col,
                     int[][] heights,
                     boolean[][] visited) {

        // Already processed this cell
        if (visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Skip out-of-bounds cells
            if (newRow < 0 || newRow >= heights.length ||
                newCol < 0 || newCol >= heights[0].length) {
                continue;
            }

            /*
             * Reverse flow condition:
             *
             * Current -> Neighbor
             *
             * Allowed only when neighbor height
             * is greater than or equal to current height.
             */
            if (heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, heights, visited);
            }
        }
    }
}