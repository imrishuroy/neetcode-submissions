class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> result) {
        // we will create a copy of path and then append it to the result, as path refence can change with every iteration
        result.add(new ArrayList<>(path));

        // we are looping from start not 0 as we don't want to include duplicates
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]); // choose
            backtrack(i + 1, nums, path, result); // backtrack
            path.remove(path.size() - 1); // undo 
        }
    }
}
