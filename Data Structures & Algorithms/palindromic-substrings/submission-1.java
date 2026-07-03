class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                boolean check = isPalinDrome(left, right, s, memo);
                if (check) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPalinDrome(int left, int right, String s, int[][] memo) {
        if (left >= right) {
            return true;
        }

        if (memo[left][right] != -1) {
            return memo[left][right] == 1;
        }

        if (s.charAt(left) != s.charAt(right)) {
            memo[left][right] = 0;
            return false; 
        }

        boolean check = isPalinDrome(left + 1, right - 1, s, memo);
        memo[left][right] = check ? 1 : 0;

        return check;
    }
}
