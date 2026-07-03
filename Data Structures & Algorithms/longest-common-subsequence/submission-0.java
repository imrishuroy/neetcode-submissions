class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return lcs(n - 1, m - 1, text1, text2, memo);

    }

    private int lcs(int i, int j, String text1, String text2, int[][] memo) {
        // invalid case
        if (i < 0 || j < 0) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            int res = 1 + lcs(i - 1, j - 1, text1, text2, memo);
            memo[i][j] = res;
            return res;
        }

        int skipText1 = lcs(i - 1, j, text1, text2, memo);
        int skipText2 = lcs(i, j - 1, text1, text2, memo);

        return memo[i][j] = Math.max(skipText1, skipText2);
    }
}
