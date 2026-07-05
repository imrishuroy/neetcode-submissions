class Solution {

    public int countSubstrings(String s) {

        int n = s.length();
        if (n == 1) {
            return 1;
        }  

        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }     

        int count = 0;

        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                if (isPalindrome(left, right, s, memo)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(int left, int right, String s, int[][] memo) {
        if (left >= right) {
            return true;
        }

        if (memo[left][right] != -1) {
            return memo[left][right] == 1;
        }

        boolean result = s.charAt(left) == s.charAt(right) && isPalindrome(left + 1, right - 1, s, memo);

        memo[left][right] = result ? 1 : 0;

        return result;
    }
}
