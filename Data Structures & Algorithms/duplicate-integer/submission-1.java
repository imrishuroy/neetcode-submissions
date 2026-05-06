class Solution {
    public boolean hasDuplicate(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0;

        for (int right = 1; right < n; right++) {
            if (nums[left] == nums[right]) return true;
            left++;
        }

        return false;
    }
}