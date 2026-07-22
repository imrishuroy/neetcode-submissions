class Solution {

    public int[] minInterval(int[][] intervals, int[] queries) {

        int n = intervals.length;
        int m = queries.length;
        int[] result = new int[m];
        Arrays.fill(result, -1);

        // sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i] = new int[]{queries[i], i};
        }

        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        // min heap : interval_length, interval_end
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int i = 0;

        for (int[] q : sortedQueries) {
            
            int queryVal = q[0];
            int queryIdx = q[1];

            while (i < n && intervals[i][0] <= queryVal) {
                int start = intervals[i][0];
                int end = intervals[i][1];

                int length = end - start + 1;
                heap.offer(new int[]{length, end});
                i++;
            }

            // remove expired intervals (end < query)
            while (!heap.isEmpty() && heap.peek()[1] < queryVal) {
                heap.poll();
            }

            if (!heap.isEmpty()) {
                result[queryIdx] = heap.peek()[0];
            }
        } 

        return result;
    }










    public int[] minInterval2(int[][] intervals, int[] queries) {
        
        int[] result = new int[queries.length];
        Arrays.fill(result, -1);

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            int minLen = Integer.MAX_VALUE;

            for (int[] interval : intervals) {
                int left = interval[0];
                int right = interval[1]; 
                if (left <= query && query <= right) {
                    minLen = Math.min(minLen, right - left + 1);
                }
            }

            result[i] = minLen == Integer.MAX_VALUE ? - 1 : minLen;
        }

        return result;

    }
}
