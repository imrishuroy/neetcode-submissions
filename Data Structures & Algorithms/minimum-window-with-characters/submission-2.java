class Solution {

    /*
     * INTUITION:
     * We want the smallest substring in `s` that contains all characters of `t`
     * (including duplicates).
     *
     * Use the Sliding Window technique:
     * - Expand the window using `right` to include characters
     * - When the window becomes valid (contains all required chars),
     *   shrink it from the left to find the minimum length window
     */

    public String minWindow(String s, String t) {

        // If t is longer than s, it's impossible to find a valid window
        if (t.length() > s.length()) return "";

        /*
         * STEP 1: Build frequency map for string `t`
         * tFreq[c] = how many times character `c` is needed
         */
        int[] tFreq = new int[128]; // ASCII characters
        for (char ch : t.toCharArray()) {
            tFreq[ch]++;
        }

        /*
         * need = number of UNIQUE characters in `t`
         * have = number of characters currently satisfied in the window
         */
        int need = 0;
        int have = 0;

        // Count how many unique characters we need to satisfy
        for (int i = 0; i < 128; i++) {
            if (tFreq[i] != 0) {
                need++;
            }
        }

        /*
         * windowFreq keeps track of character frequency in current window of `s`
         */
        int[] windowFreq = new int[128];

        int left = 0; // left pointer of sliding window

        // Store the best window indices
        int[] resultIndex = new int[] { -1, -1 };
        int minLen = Integer.MAX_VALUE;

        /*
         * STEP 2: Expand the window using `right`
         */
        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            windowFreq[ch]++;

            /*
             * If this character is required AND
             * its frequency matches exactly what we need,
             * we have satisfied one required character
             */
            if (tFreq[ch] != 0 && windowFreq[ch] == tFreq[ch]) {
                have++;
            }

            /*
             * STEP 3: When window is valid (have == need),
             * try shrinking it from the left to minimize size
             */
            while (have == need) {

                // Update result if this window is smaller
                if (right - left + 1 < minLen) {
                    resultIndex[0] = left;
                    resultIndex[1] = right;
                    minLen = right - left + 1;
                }

                /*
                 * Remove the left character from the window
                 * and move left pointer forward
                 */
                char leftCh = s.charAt(left);
                windowFreq[leftCh]--;

                /*
                 * If removing this character breaks the requirement,
                 * window is no longer valid
                 */
                if (tFreq[leftCh] != 0 && windowFreq[leftCh] < tFreq[leftCh]) {
                    have--;
                }

                left++; // shrink the window
            }
        }

        /*
         * If we never found a valid window, return empty string
         * Otherwise return the minimum window substring
         */
        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(resultIndex[0], resultIndex[1] + 1);
    }
}
