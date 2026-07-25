class Solution {
    public int maxSubArray(int[] nums) {
        int currSum = 0;
        int maxSum = nums[0];

        for (int num : nums) {
            if (currSum < 0) { // if the current sum is negative, start new
                currSum = 0;
            }
            currSum += num;

            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}
