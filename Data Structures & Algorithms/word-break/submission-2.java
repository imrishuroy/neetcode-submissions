class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> wordSet = new HashSet<>(wordDict);
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return wordBreakHelper(0, s, wordSet, memo);
    }

    private boolean wordBreakHelper(int start, String s, Set<String> wordSet, int[] memo) {
        // base case
        if (start == s.length()) {
            return true;
        }

        if (memo[start] != -1) {
            return memo[start] == 1;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);

            if (wordSet.contains(word) && wordBreakHelper(end, s, wordSet, memo)) {
                memo[start] = 1;
                return true;
            }
        }

        memo[start] = 0;
        return false;
    }
}
