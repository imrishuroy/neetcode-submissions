class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new ArrayList<>();

        list.add(intervals[0]);

        for (int i = 1; i < n; i++) {
            int prevIndex = list.size() - 1;
            int[] prevInterval = list.get(prevIndex);
            if (intervals[i][0] <= prevInterval[1]) { // current interval start time is less than or equal to prev interval end time
                prevInterval[1] = Math.max(prevInterval[1], intervals[i][1]);  
            } else {
                list.add(intervals[i]);
            }
        }

        int[][] result = new int[list.size()][2];
        int index = 0;

        for (int[] interval : list) {
            result[index++] = interval;
        }

        return result;
    }
}
