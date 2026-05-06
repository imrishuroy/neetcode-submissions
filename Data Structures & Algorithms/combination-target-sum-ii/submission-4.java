class Solution {

    /*

        Time Complexity: O(2^N * N)
        The worst case explores all subsets, leading to O(2^N) recursive calls.

        Each subset can take O(N) time to copy into the result list.

        Space Complexity: O(2^N * N)
        Recursion depth is O(N) (call stack).

        The result list can store up to O(2^N) subsets, each taking O(N) space.

    */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);  // Sort the candidates to handle duplicates easily
        dfs(0, new ArrayList<>(), target, candidates, ans);
        return ans;
    }

    private void dfs(int startIndex, List<Integer> current, int remaining, int[] candidates, List<List<Integer>> ans) {
        // Base case: if the remaining sum is zero, add a valid combination to the answer list
        if (remaining == 0) {
            ans.add(new ArrayList<>(current)); // Make a deep copy before adding
            return;
        }

        // If remaining becomes negative, stop further exploration (pruning)
        if (remaining < 0) {
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            // Skip duplicate elements to avoid duplicate combinations
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Choose the current element
            current.add(candidates[i]);

            // Explore further with the next index (i + 1) because each number can only be used once
            dfs(i + 1, current, remaining - candidates[i], candidates, ans);

            // Backtrack: remove the last added element to explore other possibilities
            current.remove(current.size() - 1);
        }
    }
}