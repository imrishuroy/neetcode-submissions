class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        
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
