class Solution {
    public int numDecodings(String s) {
        
        // 2 choices
        // Take one digit ( if its valid, i.e. not 0)
        // Take two digit (if the number is between 10 and 26)
        // So the total ways from position i = (ways if you take one digit) + (ways if you take two digits).

        int n = s.length();
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return numDecodingsHealper(0, s, memo);
    }

    private int numDecodingsHealper(int i, String s, int[] memo) {
        if (i == s.length()) {
            return 1;
        }

        if (s.charAt(i) == '0') {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int take1Char = numDecodingsHealper(i + 1, s, memo);
        
        int take2Char = 0;
        if (i <= s.length() - 2) {
            int value = Integer.parseInt(s.substring(i, i + 2));
            if (value >= 10 && value <= 26) {
              take2Char = numDecodingsHealper(i + 2, s, memo);
            }
        }

        return memo[i] =  take1Char + take2Char;
    }
}