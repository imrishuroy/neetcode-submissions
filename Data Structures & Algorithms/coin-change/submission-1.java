class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] memo = new int[n][amount + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        int result = coinChangeHelper(0, coins, amount, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int coinChangeHelper(int i, int[] coins, int amount, int[][] memo) {
        if (i >= coins.length || amount < 0) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        } 

        if (memo[i][amount] != -1) {
            return memo[i][amount];
        }

        int skip = coinChangeHelper(i + 1, coins, amount, memo);

        int take = Integer.MAX_VALUE;
        if (coins[i] <= amount) {
            int result = coinChangeHelper(i, coins, amount - coins[i], memo);
            if (result != Integer.MAX_VALUE) {
                take = 1 + result;
            }
        }

        return memo[i][amount] = Math.min(take, skip);
    }
}
