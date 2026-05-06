class Solution {
    // Recurrence Relation
    // f(n) = f(n - 1) + f(n - 2);

    public int climbStairs(int n) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    public int climbStairs3(int n) {
        
        Map<Integer, Integer> memo = new HashMap<>();

        return climbStairsMemoHelper(n, memo);

    }

    private int climbStairsMemoHelper(int n, Map<Integer, Integer> memo) {
        if (n <= 1) return 1;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int oneStep = climbStairsMemoHelper(n - 1, memo);
        int twoSteps = climbStairsMemoHelper(n - 2, memo);

        memo.put(n, oneStep + twoSteps);

        return memo.get(n);
    }









    public int climbStairs4(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;

        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
