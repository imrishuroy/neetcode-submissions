class Solution {
    static Map<Character, String> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(0, digits, new StringBuilder(), result);

        return result;
    }

    private void backtrack(int index, String digits, StringBuilder path, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        char currDigit = digits.charAt(index);
        String letter = map.get(currDigit);

        for (char ch : letter.toCharArray()) {
            path.append(ch);
            backtrack(index + 1, digits, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
