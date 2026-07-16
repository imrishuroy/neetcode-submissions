class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char ch : tasks) {
            freq[ch - 'A']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }

        int time = 0;

        while (!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int cycle = n + 1;


            while (cycle > 0 && !pq.isEmpty()) {
                int count = pq.poll();
                if (count > 1) {
                    temp.add(count - 1);
                }
                time++;
                cycle--;
            }

            pq.addAll(temp);

            if (!pq.isEmpty()) {
                time += cycle;
            }
        }

        return time;
    }
}
