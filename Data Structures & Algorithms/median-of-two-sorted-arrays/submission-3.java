class Solution {

    /*
     ==========================================================
     INTUITION
     ==========================================================
     - We do NOT need to merge both arrays.
     - Median depends only on the middle elements.
     - If we split both arrays into left and right halves such that:
         1) Left half contains exactly half of total elements
         2) Every element in left half <= every element in right half
       then we can directly compute the median.
     - Since arrays are sorted, we can use BINARY SEARCH
       to find this correct partition efficiently.

       We binary search on the smaller array to find a partition where all left-side elements are less than or equal to right-side elements, 
       allowing us to compute the median in O(log(min(m, n))) time.
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        /*
         ==========================================================
         WHY WE ALWAYS BINARY SEARCH ON THE SMALLER ARRAY
         ==========================================================
         - Binary search complexity depends on array size
         - To guarantee O(log(min(m, n))) time
         - Also simplifies edge cases
         */
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        /*
         ==========================================================
         APPROACH
         ==========================================================
         - We binary search on nums1
         - For every possible cut in nums1, we calculate the cut in nums2
         - Goal:
             left part size = (m + n + 1) / 2
         - This works for both even and odd total lengths
         */

        int left = 0;
        int right = m;

        while (left <= right) {

            /*
             ==========================================================
             WHAT WE ARE DOING
             ==========================================================
             - cut1: how many elements we take from nums1 (left side)
             - cut2: remaining elements needed from nums2
             */
            int cut1 = left + (right - left) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            /*
             ==========================================================
             WHY WE USE MIN_VALUE AND MAX_VALUE
             ==========================================================
             - Handles edge cases when partition is at array boundaries
             - Avoids out-of-bounds checks
             */

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int r1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            /*
             ==========================================================
             PARTITION VALIDATION
             ==========================================================
             - l1 <= r2 ensures nums1's left elements fit before nums2's right
             - l2 <= r1 ensures nums2's left elements fit before nums1's right
             - If both are true → correct partition found
             */

            if (l1 <= r2 && l2 <= r1) {

                /*
                 ==========================================================
                 COMPUTING MEDIAN
                 ==========================================================
                 - If total length is odd:
                     median = max(left side elements)
                 - If total length is even:
                     median = average of middle two elements
                 */

                if ((m + n) % 2 == 1) {
                    return Math.max(l1, l2);
                }

                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }

            /*
             ==========================================================
             WHY WE MOVE LEFT OR RIGHT
             ==========================================================
             - If l1 > r2:
                 We took too many elements from nums1
                 → move left
             - Else:
                 We took too few elements from nums1
                 → move right
             */

            else if (l1 > r2) {
                right = cut1 - 1;
            } else {
                left = cut1 + 1;
            }
        }

        // This line will never be reached for valid input
        return 0.0;
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
