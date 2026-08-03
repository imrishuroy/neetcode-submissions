class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, new ArrayList<>(), result, target);


        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> result, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i, nums, path, result, target - nums[i]);
            
            path.remove(path.size() - 1);
        }
    }
}
