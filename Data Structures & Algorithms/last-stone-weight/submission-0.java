class Solution {
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : stones) {
            pq.offer(num);
        }

        while (!pq.isEmpty()) {
            if (pq.size() == 1) {
                return pq.poll();
            }
            // now we have more than 2 items
            int x = pq.poll();
            int y = pq.poll();

            if (x == y) {
                continue;
            } else {
                pq.offer(x - y);
            }
        }

        return 0;
        
    }
}
