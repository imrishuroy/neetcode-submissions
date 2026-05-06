class Solution {
    // Recurrence Relation
    // f(n) = f(n - 1) + f(n - 2);


    public int climbStairs(int n) {
        
        Map<Integer, Integer> memo = new HashMap<>();

        return climbStairsMemoHelper(n, memo);

    }

    private int climbStairsMemoHelper(int n, Map<Integer, Integer> memo) {
        if (n <= 1) return 1;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int oneStep = climbStairsMemoHelper(n - 1, memo);
        int twoSteps = climbStairsMemoHelper(n - 2, memo);

        memo.put(n, oneStep + twoSteps);

        return memo.get(n);
    }









    public int climbStairs4(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;

        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
