class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        int n = nums.length;
        int case1 = robLiner(0, n - 2, nums);
        int case2 = robLiner(1, n - 1, nums);

        return Math.max(case1, case2);
    }

    private int robLiner(int start, int end, int[] nums) {

        int len = end - start + 1;
        
        int[] dp = new int[len];

        dp[0] = nums[start];

        if (len > 1) {
            dp[1] = Math.max(nums[start], nums[start + 1]);
        }
        
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
        } 

        return dp[len - 1];
    }
}
