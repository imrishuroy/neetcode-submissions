class Solution {
    public int climbStairs(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];



    //    int[] memo = new int[n + 1];
    //    Arrays.fill(memo, -1);
    //    return climbStairsHelper(n, memo);



    }
    
    // f (n) = f(n - 1) + f(n - 2)
    private int climbStairsHelper(int n, int[] memo) {
        // base case
        if (n <= 2) {
            return n;
        }

        if (memo[n] != -1) { // means its calculated earlier
            return memo[n];
        }

        // sum of choices
        int result = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        memo[n] = result;

        return memo[n];
    }
}
