class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /* cut partition both arrays such that:
            - left half contains on the left side <= right side
            - every element on the left side <= right side
            * once this condition is met -> median is easy to compute

            // we binar search on the smaller array to find the correct partition

        */

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0;
        int right = m;

        while (left <= right) {
            // partition nums1
            int cut1 = left + (right - left) / 2;

            // partition nums2 so left halves have equal size
            int cut2 = (m + n + 1) / 2 - cut1;

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int r1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            // correct partition
            if (l1 <= r2 && l2 <= r1) {
                // odd len
                if ((m + n) % 2 == 1) {
                    return Math.max(l1, l2);
                }
                // even len
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            } else if (l1 > r2) {
                right = cut1 - 1;
            }
            // move right
            else {
                left = cut1 + 1;
            }

        }

        return 0.0; // will never reach here
    
    }
    
    
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        
        int left = 0;
        int right = 0;
 
        int[] combinedList = new int[m + n];
        int index = 0;

        // combine two lists
        while (left < m && right < n) {
            if (nums1[left] <= nums2[right]) {
                combinedList[index++] = nums1[left];
                left++;
            } else {
                combinedList[index++] = nums2[right];
                right++;
            }
        }

        while (left < m) {
            combinedList[index++] = nums1[left];
            left++;
        }

        while (right < n) {
            combinedList[index++] = nums2[right];
            right++;
        }

        System.out.println(Arrays.toString(combinedList));

        int len = m + n;
        if (len % 2 != 0) { 
            return combinedList[len / 2];
        } else {
            return (combinedList[(len - 1) / 2] + combinedList[len / 2]) / 2.0;
        }
    }
}
