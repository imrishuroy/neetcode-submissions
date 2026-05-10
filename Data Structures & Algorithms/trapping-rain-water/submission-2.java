class Solution {
    public int trap(int[] height) {

        int n = height.length;
        int left = 0;
        int right = n - 1;

        int leftMax = 0;
        int rightMax = 0;
        int area = 0;

        while (left < right) {

            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                area += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                area += rightMax - height[right];
                right--;
            }
        }

        return area;
    }
}
