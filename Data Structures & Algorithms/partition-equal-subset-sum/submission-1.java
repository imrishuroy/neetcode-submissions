class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int[][] memo = new int[n][target + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return canPartitionHelper(n - 1, nums, target, memo);
    }

    private boolean canPartitionHelper(int n, int[] nums, int target, int[][] memo) {
        if (n < 0 || target < 0) {
            return false;
        }

        if (target == 0) {
            return true;
        }

        if (memo[n][target] != - 1) {
            return memo[n][target] == 1;
        }

        boolean take = canPartitionHelper(n - 1, nums, target - nums[n], memo);
        boolean skip = canPartitionHelper(n - 1, nums, target, memo);

        memo[n][target] = take || skip ? 1 : 0;

        return take || skip;
    }
}
