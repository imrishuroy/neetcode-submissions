class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // 1. if the sorted array is rotated, one the left or right part will be sorted
        // 2. find if the left part is sorted or right part is sorted

        /*

         1. at least one of the halves is always sorted in ascending order
         2. if we take middle element, we can quicly tell if our target falls within the range of that half


        */

        // Binary search to find the minimum element (pivot)
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than the rightmost element,
            // the minimum must be in the right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // Otherwise, the minimum is in the left half (including mid)
            else {
                right = mid;
            }
        }

        // left == right points to the smallest element
        return nums[left];


    }
}
