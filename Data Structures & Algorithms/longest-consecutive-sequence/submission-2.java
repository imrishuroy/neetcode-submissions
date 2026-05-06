class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        
        int maxLen = 1;
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                count++;
                maxLen = Math.max(maxLen, count);
            }else if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                count = 1;
            }
        }

        return maxLen;
    }
}
