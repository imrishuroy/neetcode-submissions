class Solution {
    // Recerrence relation => f(n) = cost[i] + Math.min(f(n - 1), f(n - 2));

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            int oneStep = cost[i] + dp[i - 1];
            int twoStep = cost[i] + dp[i -2];

            dp[i] = Math.min(oneStep, twoStep);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }

    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;

        return Math.min(minCostClimbingStairsHelper(n - 1, cost), minCostClimbingStairsHelper(n - 2, cost));
    }

    public int minCostClimbingStairsHelper(int n, int[] cost) {
        if (n == 0) return cost[0];
        if (n == 1) return cost[1];

        int oneStep = minCostClimbingStairsHelper(n - 1, cost);
        int twoStep = minCostClimbingStairsHelper(n - 2, cost);

        return cost[n] +  Math.min(oneStep, twoStep);
    }
}
