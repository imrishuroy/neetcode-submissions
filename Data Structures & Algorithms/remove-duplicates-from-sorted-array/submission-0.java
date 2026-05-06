class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int k = 0;

        while (right < n) {
            while (right < n && nums[left] == nums[right]) {
                right++;
            }
            // now right will land at the unique number
            left++;
            k++;
            if (right == n) break;
            swap(nums, left, right);
        }

        return k;

        
    }

    public static void swap (int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = nums[i];
    }
}