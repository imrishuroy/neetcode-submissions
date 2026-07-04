class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n + 1]; 

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return helper(0, -1, nums, memo);
    }

    private int helper(int i, int prevIndex, int[] nums, int[][] memo) {
        if (i >= nums.length) {
            return 0;
        }

        if (memo[i][prevIndex + 1] != -1) {
            return memo[i][prevIndex + 1];
        }

        int take = 0;

        if (prevIndex == -1 || nums[i] > nums[prevIndex]) {
            take = 1 + helper(i + 1, i, nums, memo);
        }

        int skip = helper(i + 1, prevIndex, nums, memo);

        return memo[i][prevIndex + 1] = Math.max(take, skip);
    }
}