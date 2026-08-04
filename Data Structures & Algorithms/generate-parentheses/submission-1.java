class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrack(n, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(int n, int openCount, int closeCount, StringBuilder str, List<String> result) {
        if (str.length() == 2 * n) {
            result.add(str.toString());
            return;
        }

        if (openCount < n) {
            backtrack(n, openCount + 1, closeCount, str.append('('), result);
            str.deleteCharAt(str.length() - 1);
        }

        if (closeCount < openCount) {
            backtrack(n, openCount, closeCount + 1, str.append(')'), result);
            str.deleteCharAt(str.length() - 1);
        }

        
    }
}
