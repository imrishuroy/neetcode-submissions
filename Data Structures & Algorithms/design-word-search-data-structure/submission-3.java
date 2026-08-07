class WordDictionary {

    TrieNode root;

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        } 
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        return searchW(node, 0, word);
    }

    private boolean searchW(TrieNode node, int index, String word) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isEnd;
        }
        if (word.charAt(index) == '.') {
            TrieNode[] children = node.children;
            for (TrieNode child : children) {
                if (child != null && searchW(child, index + 1, word)) {
                    return true;
                }
            }
            return false;
        }

        int charIndex = word.charAt(index) - 'a';
        if (node.children != null && node.children[charIndex] == null) {
            return false;
        } 

        return searchW(node.children[charIndex], index + 1, word);
    }
}
