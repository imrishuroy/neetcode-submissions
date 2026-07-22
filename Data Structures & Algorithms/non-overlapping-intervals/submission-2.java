class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        int n = intervals.length;
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int[] prev = intervals[0];

        int take = 1;

        for (int i = 1; i < n; i++) {
            int[] curr = intervals[i];

            if (curr[0] >= prev[1]) { // current start time is greater or equal to pre end time
                prev = curr;
                take++;
            }
        }

        return n - take;
    }
}
