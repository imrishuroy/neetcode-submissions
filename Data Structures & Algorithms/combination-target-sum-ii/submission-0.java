class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(candidates);
        backtrack(0, candidates, target, new ArrayList<>(), result);

        return List.copyOf(result);

    }

    private void backtrack(int index, int[] candidates, int target, List<Integer> path, Set<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(i + 1, candidates, target - candidates[i], path, result);
            path.remove(path.size() - 1);
        }
    }
}
