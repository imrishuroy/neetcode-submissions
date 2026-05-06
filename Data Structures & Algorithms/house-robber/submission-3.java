class Solution {

    // f(n) = Math.max(f(n - 1), f(n - 2) + nums[i]);

    public int rob(int[] nums) {
        int n = nums.length;

        if (n < 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int skip = dp[i - 1];
            int rob = dp[i - 2] + nums[i];

            dp[i] = Math.max(skip, rob);
        }

        return dp[n - 1];
    }

    public int rob3(int[] nums) {
        int n = nums.length;

        if (n < 0) return 0;

        Map<Integer, Integer> memo = new HashMap<>();

        return robMemoHelper(n - 1, nums, memo);
    }



    private int robMemoHelper(int n, int[] nums, Map<Integer, Integer> memo) {
        if (n == 0) return nums[0];
        if (n == 1) return Math.max(nums[0], nums[1]);

        if (memo.containsKey(n)) return memo.get(n);

        int rob = nums[n] + robMemoHelper(n - 2, nums, memo);
        int skip = robMemoHelper(n - 1, nums, memo);

        memo.put(n, Math.max(rob, skip));

        return memo.get(n);
    }

    public int rob4(int[] nums) {
        
        int n = nums.length;

        if (n < 0) return 0;

        return robHelper(n - 1, nums);
    }

    public int robHelper(int n, int[] nums) {
        if (n == 0) return nums[0];
        if (n == 1) return Math.max(nums[0], nums[1]);

        int rob = robHelper(n - 2, nums) + nums[n];
        int dontRob = robHelper(n - 1, nums);

        return Math.max(rob, dontRob);
    }
}
