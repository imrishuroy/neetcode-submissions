class Solution {
    public String minWindow(String s, String t) {
        
        // Edge case : impossible to form window
        if (s.length() < t.length()) return "";

        int[] tFreq = new int[128];

        for (char ch : t.toCharArray()) {
            tFreq[ch]++;
        }

        int required = 0;
        for (int freq : tFreq) {
            if (freq > 0) required++;
        }

        // sliding window frequency
        int[] windowFreq = new int[128];

        int left = 0;
        int formed = 0;

        // result tracking
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            windowFreq[ch]++;


            // if current character satisfies required frequency
            if (tFreq[ch] > 0 && windowFreq[ch] == tFreq[ch]) {
                formed++;
            }

            // shrink window while it remains valid
            while (formed == required) {
                // update ans if this window is smaller

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftCh = s.charAt(left);
                windowFreq[leftCh]--;
                left++;

                if (tFreq[leftCh] > 0 && windowFreq[leftCh] < tFreq[leftCh]) {
                    formed--;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);

    }
}
