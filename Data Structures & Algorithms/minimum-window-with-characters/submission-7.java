class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int need = tMap.size();
        int have = 0;
        int left = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        Map<Character, Integer> sMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);

            if (tMap.containsKey(ch) && tMap.get(ch).equals(sMap.get(ch))) {
                have++;
            }

            // window is valid in this while loop, that's why we are updating our windowLen inside
            // it
            while (have == need) {
                // update the window
                int windowLen = right - left + 1;
                if (windowLen < minLen) { // we got a smaller window
                    start = left;
                    minLen = windowLen;
                }

                char leftCh = s.charAt(left);

                // remove the left character from current window
                sMap.put(leftCh, sMap.get(leftCh) - 1);

                // if this character count becomes less than required,
                // our window is no longer valid
                if (tMap.containsKey(leftCh) && sMap.get(leftCh) < tMap.get(leftCh)) {
                    have--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
