class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int max = Integer.MIN_VALUE;

        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        int left = 1;
        int right = max;
        int result = max;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            long time = 0;
            for (int pile : piles) {
                time += (pile + mid - 1L) / mid;
            }

            if (time <= h) {
                result = mid; // speed is valid
                right = mid - 1; // try smaller speed
            } else {
                left = mid + 1; // need faster speed
            }
            
        }

        return result;
        
    }
}
