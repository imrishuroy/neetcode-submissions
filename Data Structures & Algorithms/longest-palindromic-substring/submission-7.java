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

        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                // String str = s.substring(left, right);
                if (isPalindrome(left, right, s)) {
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

    private boolean isPalindrome(int start, int end, String s) {
        // if (start >= end) {
        //     return true;
        // }

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
