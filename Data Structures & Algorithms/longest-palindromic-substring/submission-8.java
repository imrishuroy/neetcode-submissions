class Solution {
    private int startIndex;
    private int maxLen;

    public String longestPalindrome(String s) {
        startIndex = 0;
        maxLen = 0;
        int n = s.length();

        if (n == 1) {
            return s;
        }

        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                if (isPalindrome(left, right, s, memo)) {
                    int len = right - left + 1;
                    if (len > maxLen) {
                        startIndex = left;
                        maxLen = len;
                    }
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLen);
    }

    private boolean isPalindrome(int left, int right, String s, int[][] memo) {
        if (left >= right) {
            return true;
        }

        if (memo[left][right] != -1) { // already calculated 
            return memo[left][right] == 1;
        }

        boolean result =
            s.charAt(left) == s.charAt(right) && isPalindrome(left + 1, right - 1, s, memo);

        memo[left][right] = result ? 1 : 0;

        return result;
    }
}
