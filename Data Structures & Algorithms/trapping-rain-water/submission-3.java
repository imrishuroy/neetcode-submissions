class Solution {
    public int trap(int[] height) {
        
        int n = height.length;

        int water = 0;
        int left = 0;
        int right = n - 1;

        int leftMax = 0;
        int rightMax = 0;

        while (left < right) {
            // process left
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]); 
                water += leftMax - height[left];
                left++;
            // process right
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }

        return water;


    }
}
