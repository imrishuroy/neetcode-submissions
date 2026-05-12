class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
    
        int n = nums.length;
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int[] result = new int[n - k + 1];
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            
            while (!deque.isEmpty() && deque.peekFirst() < right - k + 1) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.pollLast();
            }

            deque.offerLast(right);

           if (right >= k - 1) {
                result[right - k + 1] = nums[deque.peekFirst()];
           }
        }

        return result;
    }

}
