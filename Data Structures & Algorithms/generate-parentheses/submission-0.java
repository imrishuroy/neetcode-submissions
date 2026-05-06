class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(0, 0, n, new StringBuilder(), result);

        return result;
    }

    private void backtrack(int openCount, int closeCount, int n, StringBuilder path, List<String> result) {
        if (path.length() == 2 * n) {
            result.add(path.toString());
        }

        if (openCount < n) {
            path.append("(");
            backtrack(openCount + 1, closeCount, n, path, result);
            path.deleteCharAt(path.length() - 1);
        }

        if (closeCount < openCount) {
            path.append(")");
            backtrack(openCount, closeCount + 1, n, path, result);
            path.deleteCharAt(path.length() - 1);
        }

        
    }
}
