class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] memo1 = new int[n + 1];
        int[] memo2 = new int[n + 1];

        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);

        int fromEnd = robFromEnd(n, nums, memo1);
        int fronSecondLast = robFromSecondLast(n - 1, nums, memo2);

        return Math.max(fromEnd, fronSecondLast);
    }

    private int robFromSecondLast(int n, int[] nums, int[] memo) {
        if (n <= 0) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int rob = nums[n - 1] + robFromSecondLast(n - 2, nums, memo);
        int skip = robFromSecondLast(n - 1, nums, memo);

        return memo[n] = Math.max(rob, skip);
    }

    private int robFromEnd(int n, int[] nums, int[] memo) {
        if (n <= 1) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int rob = nums[n - 1] + robFromEnd(n - 2, nums, memo);
        int skip = robFromEnd(n - 1, nums, memo);

        return memo[n] = Math.max(rob, skip);
    }
}
