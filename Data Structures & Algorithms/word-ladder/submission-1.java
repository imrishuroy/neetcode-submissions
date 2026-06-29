class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> wordsSet = new HashSet<>(wordList);
        int levels = 1;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
    
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return levels;
                }

                char[] arr = word.toCharArray();

                // generate all possible neighbours of word
                for (int j = 0; j < arr.length; j++) {
                    char original = arr[j];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == original) continue;
                        arr[j] = ch;
                        String newWord = new String(arr);
                        if (!visited.contains(newWord) && wordsSet.contains(newWord)) {
                            visited.add(newWord);
                            queue.offer(newWord);
                        }
                        arr[j] = original;
                    }
                }
            }

            levels++;
        }

        return 0;
    }
}