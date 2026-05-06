class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        // Put all dictionary words into a HashSet for O(1) lookup time
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return 0;

        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // start BFS from beginword
        queue.offer(beginWord);
        visited.add(beginWord);

        // beginWord itself counts as step = 1
        int steps = 1;

        // Each loop iteration process on BFS level, All words in the queue at the current moment represent transformation at the same distance from beginWord

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all node at current BFS depth
            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                // If we reached endWord, we found the shortest transformation
                if (word.equals(endWord)) return steps;

                /*
                Generate all possible words that differ by one character.
                Example "hit" -> try changing:
                h -> a..z
                i -> a..z
                t -> a..z
                */

                char[] chArray = word.toCharArray();

                for (int pos = 0; pos < chArray.length; pos++) {

                    char original = chArray[pos]; // store original char

                    // try replacing current position with every letter

                    for (char c = 'a'; c <= 'z'; c++) {

                        // skip if same character (no change)
                        if (c == original) continue;

                        chArray[pos] = c;
                        String newWord = new String(chArray);

                        /*

                        if :
                            - newword exists in dictionary
                            - and not visited before
                            Then:
                            - it's a valid next transformation
                            - add it to BFS queue
                        */

                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            visited.add(newWord);
                            queue.offer(newWord);
                        }
                    }

                    // Restore original character before next position change
                    chArray[pos] = original;

                }
               
            }

             // Finished one BFS level → increment transformation count
             steps++;
        }

        return 0;

    }
}