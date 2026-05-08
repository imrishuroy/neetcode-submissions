class Solution {
    public int[] productExceptSelf(int[] nums) {
        
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;

        for (int i = 1; i < n; i++) {
            result[i] = nums[i - 1] * result[i - 1];
        }

        int suffix = 1;
        for (int j = n - 1; j >= 0; j--) {
           result[j] *= suffix; // multiply by product of elements to the right
           suffix *= nums[j]; // update suffix to include element for next iteration
        }

        return result;
    }
}  