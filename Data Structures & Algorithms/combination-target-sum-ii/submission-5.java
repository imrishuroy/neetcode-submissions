class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
       Arrays.sort(candidates);

       List<List<Integer>> result = new ArrayList<>();

        backtrack(0, candidates, new ArrayList<>(), target, result);

       return result; 
    }

    private void backtrack(int start, int[] candidates, List<Integer> path, int target, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.add(candidates[i]);
            backtrack(i + 1, candidates, path, target - candidates[i], result);

            path.remove(path.size() - 1);
        }
    }
}
