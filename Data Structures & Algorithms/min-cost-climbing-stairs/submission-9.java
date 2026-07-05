class Solution {
    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;
        
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);


        
        // int[] memo = new int[n];
        // Arrays.fill(memo, -1);
        
        // int fromIndex0 = minCostClimbingStairsHelper(n - 1, cost, memo);
        // int fromIndex2 = minCostClimbingStairsHelper(n - 2, cost, memo);

        // return Math.min(fromIndex0, fromIndex2);
    }

    private int minCostClimbingStairsHelper(int n, int[] cost, int[] memo) {
        // base case
        if (n < 0) { // if you are beyond n, then no cost
            return 0; 
        } 

        if (n <= 1) {
            return cost[n];
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int oneStep = cost[n] + minCostClimbingStairsHelper(n - 1, cost, memo);
        int twoStep = cost[n] + minCostClimbingStairsHelper(n - 2, cost, memo);

        return memo[n] = Math.min(oneStep, twoStep);
    }
}
