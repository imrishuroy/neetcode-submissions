class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // we square not square root
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> squaredDist(b) - squaredDist(a));

        for (int[] point : points) {
            pq.offer(point);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] result = new int[k][2];
        int index = 0;
        
        while (!pq.isEmpty()) {
            int[] point = pq.poll();
            result[index++] = point;
        }

        return result;

    }

    private int squaredDist(int[] points) {
        return points[0] * points[0] + points[1] * points[1];
    }
}
