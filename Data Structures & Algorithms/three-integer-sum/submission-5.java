class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        /*
         * Intuition:
         *
         * We need to find 3 numbers whose sum = 0.
         *
         * Brute force:
         * Try every possible triplet using 3 loops -> O(n^3)
         *
         * Better approach:
         * 1. Sort the array
         * 2. Fix one number
         * 3. Use two pointers to find the other two numbers
         *
         * Why sorting helps?
         * - We can move pointers intelligently
         * - Easy to skip duplicates
         *
         * Example:
         * nums = [-4, -1, -1, 0, 1, 2]
         *
         * Fix -1
         * Now we need two numbers whose sum = 1
         *
         * left -> -1
         * right -> 2
         *
         * If sum is too small -> move left forward
         * If sum is too large -> move right backward
         */

        int n = nums.length;

        // Sort the array for two pointer approach
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        // Pick one number at a time
        for (int i = 0; i < n; i++) {
            // Skip duplicate starting numbers
            // to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // We now need two numbers whose sum equals:
            // target = -nums[i]
            int target = 0 - nums[i];

            // Two pointers
            int left = i + 1;
            int right = n - 1;

            // Search for pair
            while (left < right) {
                int sum = nums[left] + nums[right];

                // Found valid triplet
                if (sum == target) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // Move both pointers
                    left++;
                    right--;

                    // Skip duplicate numbers on left side
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate numbers on right side
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                    // Sum is too large
                    // Move right pointer to reduce sum
                } else if (sum > target) {
                    right--;

                    // Sum is too small
                    // Move left pointer to increase sum
                } else {
                    left++;
                }
            }
        }

        return result;
    }
}