class Solution {
    private int start;
    private int maxLen;

    public String longestPalindrome(String s) {
        int n = s.length();

        start = 0;
        maxLen = 1;

        // -1 not computed
        // 0 not palindrome
        // 1 palindrome

        int[][] memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                
                if (isPalindrome(left, right, s, memo)) {
                    int len = right - left + 1;
                    if (len > maxLen) {
                        maxLen = len;
                        start = left;
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);

    }

    private boolean isPalindrome(int left, int right, String s, int[][] memo) {
        
        // Empty string or single character is always a palindrome
        if (left >= right) {
            return true;
        }

        if (memo[left][right] != -1) { // already computed
            return memo[left][right] == 1;
        }

        // first and last character must match
        if (s.charAt(left) != s.charAt(right)) {
            memo[left][right] = 0;
            return false;
        }

        // check for inner substring
        boolean result = isPalindrome(left + 1, right - 1, s, memo);
        memo[left][right] = result ? 1 : 0;

        return result;
    }
}
