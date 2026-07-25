class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        } 

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int h : hand) {
            freqMap.put(h, freqMap.getOrDefault(h, 0) + 1);
        }

        while (!freqMap.isEmpty()) {
            int smallest = freqMap.firstKey();

            for (int i = 0; i < groupSize; i++) {
                if (freqMap.containsKey(smallest)) {
                    freqMap.put(smallest, freqMap.get(smallest) - 1);
                    if (freqMap.get(smallest) <= 0) {
                        freqMap.remove(smallest);
                    }
                    smallest++;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
