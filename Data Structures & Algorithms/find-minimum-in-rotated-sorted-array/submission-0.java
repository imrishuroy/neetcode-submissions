class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // 1. if the sorted array is rotated, one the left or right part will be sorted
        // 2. find if the left part is sorted or right part is sorted

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return nums[left];
    }
}
