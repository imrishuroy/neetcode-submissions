class Solution {

    private static int[][] moves = {
        {2, 1}, {1, 2}, {-1, 2}, {2, -1},
        {-2, 1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    public int minKnightMoves(int x, int y) {
        return bfs(Math.abs(x), Math.abs(y));
    }

    private int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new int[]{0, 0, 0});
        visited.add("0,0");

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();

                int r = node[0];
                int c = node[1];
                int dist = node[2];

                if (r == x && c == y) return dist;

                for (int[] move : moves) {
                    int nRow = r + move[0];
                    int nCol = c + move[1];

                    if (!visited.contains(nRow + "," + nCol)) {
                        visited.add(nRow + "," + nCol);
                        queue.offer(new int[]{nRow, nCol, dist + 1});
                    }
                } 
            }
        }

        return -1;
    }
}
