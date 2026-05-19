class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        // k = bananas per hour eating rate
        int max = 0;

        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        int left = 1;
        int right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canEat(mid, h, piles)) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }

        return left;
        
    }

    private boolean canEat(int speed, int h, int[] piles) {
        if (speed == 0) return false;
        long totalHour = 0;
        for (int i = 0; i < piles.length; i++) {
            totalHour += (piles[i] + speed - 1L) / speed;
        }

        return totalHour <= h;
    }
}
