class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return decodeWaysHelper(0, s, memo);
    }

    private int decodeWaysHelper(int i, String s, int[] memo) {
        if (i > s.length()) {
            return 0;
        }

        if (i == s.length()) {
            return 1;
        }

        if (s.charAt(i) == '0') {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int takeOneCh = decodeWaysHelper(i + 1, s, memo);

        int takeTwoCh = 0;

        if (i <= s.length() - 2) {
            int value = Integer.parseInt(s.substring(i, i + 2));
            if (value >= 10 && value <= 26) {
                takeTwoCh = decodeWaysHelper(i + 2, s, memo);
            }
        }

        return memo[i] = takeOneCh + takeTwoCh;
    }
}
