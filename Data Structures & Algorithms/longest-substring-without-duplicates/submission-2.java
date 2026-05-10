class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        int longestLen = 0;

        for (int right = 0; right < s.length(); right++) {
            // 1. expand the window towards right
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            // 2. Shrink the window until it become valid
            while (map.get(s.charAt(right)) > 1) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }
                left++;
            }
            // 3. When the window is valid, update the result
            longestLen = Math.max(longestLen, right - left + 1);
        }

        return longestLen;
    }
}
