class Solution {
    public int lengthOfLongestSubstring(String s) {

        int longest = 0;
        int left = 0;
        Set<Character> seen = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {

            char curr = s.charAt(right);

            while (seen.contains(curr)) {
                seen.remove(s.charAt(left));
                left++;
            }
            
            seen.add(curr);
            longest = Math.max(longest, right - left + 1);

        }

        return longest;
        
    }
}
