class Solution {

    /*
     * =========================
     * INTUITION
     * =========================
     *
     * Koko can eat bananas at a fixed speed `k` (bananas/hour).
     * For a given speed:
     *   - We can calculate how many hours it will take to eat all piles.
     *
     * Observations:
     * - If Koko eats faster, total time required DECREASES.
     * - If Koko eats slower, total time required INCREASES.
     *
     * This creates a MONOTONIC relationship:
     *   speed ↑  =>  time ↓
     *
     * When a function is monotonic, we can apply BINARY SEARCH.
     */

    public int minEatingSpeed(int[] piles, int h) {

        /*
         * =========================
         * APPROACH (Binary Search)
         * =========================
         *
         * 1. Minimum possible speed = 1 banana/hour
         * 2. Maximum possible speed = max pile size
         *    (eating more than the largest pile gives no benefit)
         *
         * We binary search on the "answer space" (possible eating speeds)
         * and check if a given speed allows Koko to finish within `h` hours.
         */

        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        int left = 1;
        int right = max;
        int speed = max; // stores the best (minimum valid) speed found

        while (left <= right) {

            // mid represents a candidate eating speed
            int mid = left + (right - left) / 2;

            // total time required if Koko eats at speed = mid
            long time = timeTaken(piles, mid);

            if (time <= h) {
                /*
                 * If Koko can finish within h hours:
                 * - mid is a VALID speed
                 * - try to find a smaller speed (optimize)
                 */
                speed = Math.min(speed, mid);
                right = mid - 1;
            } else {
                /*
                 * If time exceeds h:
                 * - speed is too slow
                 * - we must increase the eating speed
                 */
                left = mid + 1;
            }
        }

        return speed;
    }

    /*
     * =========================
     * BRUTE FORCE (for understanding)
     * =========================
     *
     * Tries all speeds from 1 to max.
     * Correct but VERY slow (O(n * max)) and will TLE.
     */
    public int minEatingSpeed2(int[] piles, int h) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        int speed = max;

        for (int i = 1; i <= max; i++) {
            long time = timeTaken(piles, i);
            if (time <= h) {
                speed = Math.min(speed, i);
            }
        }
        return speed;
    }

    /*
     * =========================
     * TIME CALCULATION
     * =========================
     *
     * For each pile:
     *   time = ceil(pile / rate)
     *
     * We SUM time across all piles.
     *
     * NOTE:
     * - We use `long` because the total time can overflow `int`
     *   for large inputs.
     */
    public long timeTaken(int[] piles, int rate) {
        long time = 0;

        for (int i = 0; i < piles.length; i++) {
            time += (piles[i] + (rate - 1L)) / rate;
        }

        return time;
    }
}
