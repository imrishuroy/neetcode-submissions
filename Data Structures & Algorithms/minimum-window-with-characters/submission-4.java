class Solution {
    public String minWindow(String s, String t) {
        String result = "";
        Map<Character, Integer> tFreq = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char key = t.charAt(i);
            tFreq.put(key, tFreq.getOrDefault(key, 0) + 1);
        }

        int required = tFreq.size();

        int left = 0;
        int formed = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        Map<Character, Integer> windowFreq = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char key = s.charAt(right);
            windowFreq.put(key, windowFreq.getOrDefault(key, 0) + 1);
            if (tFreq.containsKey(key) && windowFreq.get(key) == tFreq.get(key)) {
                formed++;
            }

            // when we have found all the characters for t in s, ( valid window ), shrink the window
            while (required == formed) {
                // update min window
                int windowLen = right - left + 1;

                if (windowLen < minLen) {
                    minLen = windowLen;
                    start = left;
                }

                char leftCh = s.charAt(left);
                windowFreq.put(leftCh, windowFreq.get(leftCh) - 1);

                if (tFreq.containsKey(leftCh) && windowFreq.get(leftCh) < tFreq.get(leftCh)) {
                    formed--;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
