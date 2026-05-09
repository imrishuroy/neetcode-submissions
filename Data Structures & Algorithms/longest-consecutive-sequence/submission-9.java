class Solution {
    public int longestConsecutive(int[] nums) {
        // Edge case:
        // If array is empty, there is no sequence.
        if (nums.length == 0) {
            return 0;
        }

        // Store all numbers inside a HashSet.
        // WHY?
        // HashSet gives O(1) average lookup time,
        // which helps us quickly check:
        // "Does the next number exist?"
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Minimum possible answer is 1
        // because at least one number exists.
        int maxLen = 1;

        // Iterate through unique numbers only.
        for (int num : set) {
            // IMPORTANT IDEA:
            // We only start counting if current number
            // is the START of a sequence.
            //
            // Example:
            // For sequence [1,2,3,4]
            // we only start from 1 because:
            // 0 does NOT exist.
            //
            // We skip 2,3,4 because they are already
            // part of a sequence counted earlier.
            //
            // This optimization makes solution O(n).
            if (!set.contains(num - 1)) {
                // Current number is start of sequence
                int currNum = num;

                // Current sequence length
                int len = 1;

                // Keep checking next consecutive numbers
                //
                // Example:
                // 1 -> 2 -> 3 -> 4
                while (set.contains(currNum + 1)) {
                    // Move to next number in sequence
                    currNum++;

                    // Increase sequence length
                    len++;
                }

                // Update maximum sequence found so far
                maxLen = Math.max(maxLen, len);
            }
        }

        return maxLen;
    }

    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int maxLen = 1;
        int currLen = 1;

        for (int i = 1; i < nums.length; i++) {
            // skip the duplicates
            if (nums[i] == nums[i - 1]) {
                continue;
            }

            if (nums[i] == nums[i - 1] + 1) {
                currLen++;
            } else {
                currLen = 1;
            }

            maxLen = Math.max(maxLen, currLen);
        }

        return maxLen;
    }
}
