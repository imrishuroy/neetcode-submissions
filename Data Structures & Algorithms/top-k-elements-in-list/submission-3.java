class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        // Map to store:
        // key   -> number
        // value -> frequency/count of that number
        Map<Integer, Integer> map = new HashMap<>();
        
        // Min Heap:
        // int[0] -> number
        // int[1] -> frequency
        //
        // We use a MIN heap so the element with the
        // smallest frequency stays at the top.
        //
        // Why?
        // Because we only want to keep the top K frequent elements.
        // If heap size becomes > k, we remove the smallest frequency element.
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // Step 1: Count frequency of each number
        //
        // Example:
        // nums = [1,1,1,2,2,3]
        //
        // map becomes:
        // 1 -> 3
        // 2 -> 2
        // 3 -> 1
        //
        // Time Complexity: O(n)
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Step 2: Maintain top K frequent elements using min heap
        //
        // We iterate through all unique elements in the map.
        //
        // Push {number, frequency} into heap.
        //
        // If heap size exceeds k:
        // remove the smallest frequency element.
        //
        // This ensures heap always contains
        // only the K most frequent elements.
        //
        // Example:
        // k = 2
        //
        // Heap after processing:
        // [2,2]
        // [1,3]
        //
        // 3 with frequency 1 gets removed.
        //
        // Time Complexity:
        // Let m = unique elements
        // Each heap operation = O(log k)
        //
        // Total = O(m log k)
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            // Add current number and its frequency
            minHeap.offer(new int[]{
                    entry.getKey(),
                    entry.getValue()
            });

            // Keep only top K elements in heap
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Result array to store answer
        int[] result = new int[k];
        int index = 0;

        // Step 3: Extract elements from heap
        //
        // Heap contains top K frequent elements.
        //
        // We only store the number (index 0),
        // not the frequency.
        //
        // Time Complexity: O(k log k)
        // (poll operation k times)
        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll()[0];
        }

        return result;
    }
}