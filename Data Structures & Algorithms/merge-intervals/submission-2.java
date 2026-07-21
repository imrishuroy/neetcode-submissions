class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new ArrayList<>();

        list.add(intervals[0]);

        for (int i = 1; i < n; i++) {
            int[] last = list.get(list.size() - 1);
            int[] curr = intervals[i];

            if (curr[0] <= last[1]) { // current interval start time is less than or equal to prev interval end time
                last[1] = Math.max(last[1], curr[1]);  
            } else {
                list.add(curr);
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
