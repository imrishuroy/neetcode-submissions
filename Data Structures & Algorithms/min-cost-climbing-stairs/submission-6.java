class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return minCostClimbingStairsHelper(cost.length, cost, memo);
    }

    private int minCostClimbingStairsHelper(int n, int[] cost, int[] memo) {
        if (n <= 1) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int oneStep = cost[n - 1] + minCostClimbingStairsHelper(n - 1, cost, memo);
        int twoStep = cost[n - 2] +  minCostClimbingStairsHelper(n - 2, cost, memo);

        return memo[n] = Math.min(oneStep, twoStep);
    }
}
