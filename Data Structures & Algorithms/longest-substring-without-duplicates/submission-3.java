class Solution {
    public int lengthOfLongestSubstring(String s) {

        int left = 0;
        Set<Character> seen = new HashSet<>();
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left));
                left++;
            }

            // now the window is valid
            seen.add(s.charAt(right));

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
        
    }
}
