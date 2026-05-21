class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // if even numbers after combining two lists, then median is  sum of two middle numbers / 2
        // if odd numbers then the middle elememen is the median

        int n1 = nums1.length;
        int n2 = nums2.length;

        int i = 0;
        int j = 0;
        int k = 0;

        int[] merged = new int[n1 + n2];


        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // add remaining numbers
        while (i < n1) {
            merged[k++] = nums1[i++];
        }

        while (j < n2) {
            merged[k++] = nums2[j++];
        }

        int total = n1 + n2;

        if (total % 2 == 1) { // odd
            return merged[total / 2];
        }

        int mid1 = merged[(total / 2) - 1];
        int mid2 = merged[total / 2];

        return (mid1 + mid2) / 2.0;

    }
}
