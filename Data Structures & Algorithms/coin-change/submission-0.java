class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] memo = new int[n][amount + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        int result = coinChangeHelper(n - 1, coins, amount, memo);
        return result == Integer.MAX_VALUE ? - 1 : result;
    }

    private int coinChangeHelper(int n, int[] coins, int amount, int[][] memo) {
        // base case

        if (amount == 0) {
            return 0;
        }

        if (n < 0 || amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (memo[n][amount] != -1) {
            return memo[n][amount];
        }

        int skip = coinChangeHelper(n - 1, coins, amount, memo);

        int take = Integer.MAX_VALUE;

        if (coins[n] <= amount) {
            int res = coinChangeHelper(n, coins, amount - coins[n], memo);
            if (res != Integer.MAX_VALUE) {
                take = 1 + res;
            }
        }        

        return memo[n][amount] =  Math.min(skip, take);
    }
}
