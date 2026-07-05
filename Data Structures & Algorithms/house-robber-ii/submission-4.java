class Solution {
    public int rob(int[] nums) {

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] memo1 = new int[n];
        int[] memo2 = new int[n];

        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);

        int fromEnd = robFromEnd(n - 1, nums, memo1);
        int fromSecondLast = robFromLastSecond(n - 2, nums, memo2);

        return Math.max(fromEnd, fromSecondLast);
        
    }

    private int robFromEnd(int n, int[] nums, int[] memo) {
        if (n <= 0) { // we can't rob the first house
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int rob = nums[n] + robFromEnd(n - 2, nums, memo);
        int skip = robFromEnd(n - 1, nums, memo);

        return memo[n] = Math.max(rob, skip);
    }

    private int robFromLastSecond(int n, int[]nums, int[] memo) {
        if (n < 0) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        } 

        int rob = nums[n] + robFromLastSecond(n - 2, nums, memo);
        int skip = robFromLastSecond(n -1, nums, memo);

        return memo[n] = Math.max(rob, skip);
    }
}
