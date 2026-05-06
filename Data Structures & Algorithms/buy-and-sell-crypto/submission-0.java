class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int maxProfit = 0;
        int minSoFar = prices[0];

        for (int i = 0; i < n; i++) {
            minSoFar = Math.min(minSoFar, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minSoFar);
        }

        return maxProfit;
    }
}
