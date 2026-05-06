class Solution {

    private final Map<Character, String> phoneBook = new HashMap<>();
    


    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();

        phoneBook.put('2', "abc");
        phoneBook.put('3', "def");
        phoneBook.put('4', "ghi");
        phoneBook.put('5', "jkl");
        phoneBook.put('6', "mno");
        phoneBook.put('7', "pqrs");
        phoneBook.put('8', "tuv");
        phoneBook.put('9', "wxyz");

        List<String> result = new ArrayList<>();

        backtrack(0, digits, new StringBuilder(), result);

        return result;
        
    }

    private void backtrack(int index, String digits, StringBuilder path, List<String> result) {
        // base case
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        char currentDigit = digits.charAt(index);
        for (char ch : phoneBook.get(currentDigit).toCharArray()) {
            path.append(ch);
            backtrack(index + 1, digits, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
