class Solution {
    public int lastStoneWeight(int[] stones) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (!pq.isEmpty() && pq.size() > 1) {
            int x = pq.poll(); // x will be bigger
            int y = pq.poll();

            if (x == y) {
                continue;
            }

            pq.offer(x - y);
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }

    
}
