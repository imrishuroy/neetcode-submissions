class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        // Phase 1: Add intervals that end before new one starts
        // interval.end < new.start, ends before new starts
        List<int[]> result = new ArrayList<>();
        int index = 0;

        while (index < n && intervals[index][1] < newInterval[0]) {
            result.add(intervals[index]);
            index++;
        }

        // Phase 2: Merge all overlapping intervals
        // interval.start <= new end, overlaps with merge, expand it
        while (index < n && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[index][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[index][1], newInterval[1]);
            index++;
        }

        result.add(newInterval);

        // Phase 3: Add Intervals that start after merged one ends
        // interval.start > merged.end, starts after the merged end add remaining
        while (index < n) {
            result.add(intervals[index]);
            index++;
        }

        return result.toArray(new int[0][]);
    }
}
