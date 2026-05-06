class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Freq = new int[26];
        int[] windowFreq = new int[26];
        

        for (char ch : s1.toCharArray()) {
            s1Freq[ch - 'a']++;
        }

        int left = 0;

        for (int right = 0; right < s2.length(); right++) {
            windowFreq[s2.charAt(right) - 'a']++;

            while (right - left + 1 > s1.length()) {
                windowFreq[s2.charAt(left) - 'a']--;
                left++;
            }

            if (Arrays.equals(s1Freq, windowFreq)) return true; // O(1)
        }

        return false;

    }
}
