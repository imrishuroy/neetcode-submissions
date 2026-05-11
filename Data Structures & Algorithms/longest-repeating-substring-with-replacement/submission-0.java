class Solution {

    Map<Character, Integer> freqMap = new HashMap<>();

    public int characterReplacement(String s, int k) {
    int[] freq = new int[26];
    int left = 0, maxFreq = 0, maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        freq[s.charAt(right) - 'A']++;
        maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);
        
        // SHRINK if we need to replace more than k
        while (right - left + 1 - maxFreq > k) {
            freq[s.charAt(left) - 'A']--;
            left++;
        }
        
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}

    public int characterReplacement2(String s, int k) {

        int left = 0;
        int maxLen = 0;
        int maxFreq = 0;

        for (int right = 0; right < s.length(); right++) {


            char key = s.charAt(right);
            freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
            maxFreq = Math.max(maxFreq, freqMap.get(key));

            while ((right - left + 1) - maxFreq > k) {
                freqMap.put(s.charAt(left), freqMap.get(s.charAt(left)) - 1);
                for (int count : freqMap.values()) {
                    maxFreq = Math.max(maxFreq, count);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
        
    }
}
