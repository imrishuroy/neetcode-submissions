class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        HashSet<String> wordSet = new HashSet<>(wordDict);
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return wordBreakHelper(0, s, wordSet, memo);
    }

    private boolean wordBreakHelper(int startIndex, String s, HashSet<String> wordSet, int[] memo) {
        if (startIndex == s.length()) {
            return true;
        }

        if (memo[startIndex] != -1) {
            return memo[startIndex] == 1;
        }

        for (int endIndex = startIndex + 1; endIndex <= s.length(); endIndex++) {
            String word = s.substring(startIndex, endIndex);

            if (wordSet.contains(word)) {
                boolean result = wordBreakHelper(endIndex, s, wordSet, memo);
                memo[startIndex] = result ? 1 : 0;
                if (result) return true;
            }
        }

        memo[startIndex] = 0;
        return false;
        
    }

}