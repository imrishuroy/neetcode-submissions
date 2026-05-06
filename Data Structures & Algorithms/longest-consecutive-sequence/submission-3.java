class Solution {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int longest = 0;
        for (int num : set) {
            // only start counting if this is first number in the sequence
            if (!set.contains(num - 1)) {
                int len = 1;

                while (set.contains(num + len)) {
                    len++;
                }
                longest = Math.max(longest, len);
            }
        }

        return longest;
        
    }







    public int longestConsecutive2(int[] nums) {
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
