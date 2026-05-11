class Solution {
    public boolean checkInclusion(String s1, String s2) {

        int[] s1Freq = new int[26];
        int[] s2Freq = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Freq[s1.charAt(i) - 'a']++;
        }

        int left = 0;
        int k = s1.length();

        for (int right = 0; right < s2.length(); right++) {
            s2Freq[s2.charAt(right) - 'a']++;

            if (right - left + 1 > k) {
                s2Freq[s2.charAt(left) - 'a']--;
                left++;
            }

            if (Arrays.equals(s1Freq, s2Freq)) {
                return true;
            }
        }


        return false;
        
    }
}
