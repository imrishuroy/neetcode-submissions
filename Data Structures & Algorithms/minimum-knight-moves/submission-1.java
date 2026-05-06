class Solution {

    /*
     * All 8 possible knight moves.
     * A knight moves in an L-shape: (2,1) or (1,2) in all directions.
     */
    private static final int[][] MOVES = {
        {2, 1}, {1, 2}, {-1, 2}, {2, -1},
        {-2, 1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    /*
     * INTUITION:
     * The chessboard is infinite. We need the minimum number of moves
     * for a knight starting from (0,0) to reach (x,y).
     *
     * A knight's movement is symmetric in all quadrants.
     * So reaching (x,y) is the same as reaching (|x|, |y|).
     * This reduces the search space by 75%.
     */
    public int minKnightMoves(int x, int y) {
        return bfs(Math.abs(x), Math.abs(y));
    }

    /*
     * APPROACH:
     * This is a shortest-path problem on an unweighted graph.
     * Each cell is a node, each knight move is an edge.
     *
     * Since every move has equal cost (1 move),
     * Breadth-First Search (BFS) guarantees the shortest path.
     */
    private int bfs(int targetX, int targetY) {

        // Queue stores {row, col, distanceFromStart}
        Queue<int[]> queue = new LinkedList<>();

        // Visited set prevents revisiting same coordinates
        // We store coordinates as "x,y" strings
        Set<String> visited = new HashSet<>();

        // Start BFS from origin (0,0) with distance 0
        queue.offer(new int[]{0, 0, 0});
        visited.add("0,0");

        // Standard BFS loop
        while (!queue.isEmpty()) {

            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            int dist = node[2];

            // If target reached → shortest path found
            if (x == targetX && y == targetY) {
                return dist;
            }

            // Explore all 8 possible knight moves
            for (int[] move : MOVES) {

                int nextX = x + move[0];
                int nextY = y + move[1];

                String key = nextX + "," + nextY;

                // If not visited before, push into queue
                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.offer(new int[]{nextX, nextY, dist + 1});
                }
            }
        }

        // Theoretically unreachable on infinite board
        return -1;
    }
}
