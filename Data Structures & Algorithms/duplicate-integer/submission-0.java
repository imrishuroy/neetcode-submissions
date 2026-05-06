class Solution {
    public boolean hasDuplicate(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0;
        int right = 1;

        while (right < n) {
            if (nums[left] == nums[right]) return true;
            left++;
            right++;
        } 

        return false;
    }
}