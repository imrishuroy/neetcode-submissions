class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        int[] output = new int[n - k + 1];

        for (int right = 0; right <= n - k; right++) {
            int maxi = nums[right];
            for (int j = right; j < right + k; j++) {
                maxi = Math.max(maxi, nums[j]);
            }
            output[right] = maxi;
        }


        return output;
        
    }
}
