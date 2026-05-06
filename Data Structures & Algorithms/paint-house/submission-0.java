class Solution {
    public int minCost(int[][] costs) {
        // we need to keep track of prev color
        int n = costs.length;
        int[][] memo = new int[n + 1][4];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return minCostHelper(0, -1, costs, memo);
    }

    private int minCostHelper(int index, int prevColor, int[][] costs, int[][] memo) {
        if (index == costs.length) {
            return 0;
        }

        if (memo[index][prevColor + 1] != -1) {
            return memo[index][prevColor + 1];
        }

        int minCost = Integer.MAX_VALUE;

        for (int color = 0; color < 3; color++) {
            if (color == prevColor) {
                continue;
            }

            int cost = costs[index][color] + minCostHelper(index + 1, color, costs, memo);

            minCost = Math.min(minCost, cost);
        }

        return memo[index][prevColor + 1] = minCost;
    }
}