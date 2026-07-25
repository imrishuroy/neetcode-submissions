class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        } 
        
        // Count frequency of each card, in soreted order
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int h : hand) {
            freqMap.put(h, freqMap.getOrDefault(h, 0) + 1);
        }

        while (!freqMap.isEmpty()) {
            int first  = freqMap.firstKey();

            for (int i = 0; i < groupSize; i++) {
                int card = first + i;
                if (!freqMap.containsKey(card)) {
                    return false;
                }
                int freq = freqMap.get(card);
                if (freq == 1) {
                    freqMap.remove(card);
                } else {
                    freqMap.put(card, freq - 1);
                }
            }
        }

        return true;
    }
}
