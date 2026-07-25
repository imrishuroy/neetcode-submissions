class Solution {
    public boolean canJump(int[] nums) {
        
        int maxReachIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (maxReachIndex < i) {
                return false;
            }

            if (maxReachIndex >= nums.length - 1) {
                return true;
            }

            maxReachIndex = Math.max(maxReachIndex, i + nums[i]); // i.e. index + number at that position
        }

        return false;
    }
}
