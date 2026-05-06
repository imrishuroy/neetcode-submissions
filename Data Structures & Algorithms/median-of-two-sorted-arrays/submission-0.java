class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

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
