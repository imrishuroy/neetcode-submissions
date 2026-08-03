class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        
        backtrack(0, nums, new ArrayList<>(), set);
        List<List<Integer>> result = new ArrayList<>(set);
        return result;


    }

    private void backtrack(int start, int[] nums, List<Integer> path, Set<List<Integer>> set) {
        set.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums, path, set);
            path.remove(path.size() - 1);
        }
    }
}
