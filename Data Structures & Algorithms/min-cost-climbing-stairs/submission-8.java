class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        
        int fromIndex0 = minCostClimbingStairsHelper(n - 1, cost, memo);
        int fromIndex2 = minCostClimbingStairsHelper(n - 2, cost, memo);

        return Math.min(fromIndex0, fromIndex2);
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
