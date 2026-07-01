class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return robHelper(n, nums, memo);
    }

    private int robHelper(int n, int[] nums, int[] memo) {
        // base case
        if (n <= 0) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int rob = nums[n - 1] + robHelper(n - 2, nums, memo); // because you can't rob adjacent house
        int skip = robHelper(n - 1, nums, memo);

        return memo[n] = Math.max(rob, skip);
    }
}
