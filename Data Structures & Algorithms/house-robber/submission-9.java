class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1]; // the last index


        // int[] memo = new int[n];
        // Arrays.fill(memo, -1);

        // return robHelper(n - 1, nums, memo);
    }

    private int robHelper(int n, int[] nums, int[] memo) {
        // base case
        if (n < 0) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int rob = nums[n] + robHelper(n - 2, nums, memo);
        int skip = robHelper(n - 1, nums, memo);

        return memo[n] = Math.max(rob, skip);
    }
}
