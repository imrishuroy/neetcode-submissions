class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return lengthOfLISHelper(0, -1, nums, memo);
    }

    private int lengthOfLISHelper(int index, int prevIndex, int[] nums, int[][] memo) {
        if (index == nums.length) {
            return 0;
        }

        if (memo[index][prevIndex + 1] != -1) {
            return memo[index][prevIndex + 1];
        } 

        int take = 0;
        // if its first index or curr index num is greater than prev index
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) { 
            take = 1 + lengthOfLISHelper(index + 1, index, nums, memo);
        }

        int skip = lengthOfLISHelper(index + 1, prevIndex, nums, memo);

        return memo[index][prevIndex + 1] = Math.max(take, skip);
    }
}
