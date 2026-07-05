class Solution {
    public int maxProduct(int[] nums) {
        
        int maxCurrent = nums[0];
        int minCurrent = nums[0];
        int maxProduct = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int prevMax = maxCurrent;
            int prevMin = minCurrent;

            maxCurrent = Math.max(nums[i], Math.max(nums[i] * prevMax, nums[i] * prevMin));
            minCurrent = Math.min(nums[i], Math.min(nums[i] * prevMax, nums[i] * prevMin));

            maxProduct = Math.max(maxProduct, maxCurrent);
        }


        return maxProduct;
    }
}
