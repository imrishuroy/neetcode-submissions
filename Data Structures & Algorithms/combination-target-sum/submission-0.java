class Solution {

    // =======================
    // APPROACH INTUITION:
    // =======================
    // We need to find all combinations of numbers that sum up to a target.
    // Each number can be chosen unlimited times.
    //
    // This is a classic "decision tree + backtracking" problem.
    // At every step, we decide:
    //   → Either pick the current number again (since reuse is allowed)
    //   → Or move forward to try next numbers.
    //
    // We explore all possible paths where the running sum decreases toward zero.
    // If the sum becomes exactly zero → we found a valid combination.
    // If the sum becomes negative → this path is invalid, so we stop exploring it.
    //
    // Sorting helps ensure combinations are built in non-decreasing order,
    // preventing duplicates in different orders.

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // Sort to maintain order and avoid duplicate permutations, not required always
        Arrays.sort(nums); 

        // Start backtracking from index 0 with empty path
        backtrack(0, nums, target, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int index, int[] nums, int target,
                           List<Integer> path,
                           List<List<Integer>> result) {

        // =======================
        // BASE CASES
        // =======================

        // If target becomes exactly 0, we found a valid combination
        if (target == 0) {
            result.add(new ArrayList<>(path)); // copy current path
            return;
        }

        // If target goes below 0, this path cannot produce a valid sum
        if (target < 0) return;

        // =======================
        // EXPLORATION
        // =======================
        // Try each number starting from 'index'
        // (not from 0) to avoid reordering duplicates
        for (int i = index; i < nums.length; i++) {

            // Choose current number
            path.add(nums[i]);

            // Recurse:
            // - We pass 'i' again (not i+1) because the same element
            //   can be reused unlimited times.
            // - Reduce target by chosen number
            backtrack(i, nums, target - nums[i], path, result);

            // Undo the choice (backtrack) to explore next options
            path.remove(path.size() - 1);
        }
    }
}

