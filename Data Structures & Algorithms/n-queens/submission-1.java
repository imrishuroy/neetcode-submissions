class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> mainDiagonal = new HashSet<>();
        Set<Integer> antiDiagonal = new HashSet<>();

        backtrack(0, n, cols, mainDiagonal, antiDiagonal, new ArrayList<>(), result);


        return result;
    }

    private void backtrack(int row, int n, Set<Integer> cols, Set<Integer> mainDiagonal, Set<Integer> antiDiagonal, List<String> board, List<List<String>> result) {
        // base case
        if (row == n) {
            result.add(new ArrayList<>(board));
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row - col;
            int d2 = row + col;

            if (cols.contains(col)) {
                continue;
            }
            if (mainDiagonal.contains(d1)) {
                continue;
            }
            if (antiDiagonal.contains(d2)) {
                continue;
            }
            

            cols.add(col);
            mainDiagonal.add(d1);
            antiDiagonal.add(d2);

            char[] rowArr = new char[n];
            Arrays.fill(rowArr, '.');
            rowArr[col] = 'Q';
            board.add(new String(rowArr));

            backtrack(row + 1, n, cols, mainDiagonal, antiDiagonal, board, result);
            
            cols.remove(col);
            mainDiagonal.remove(d1);
            antiDiagonal.remove(d2);
            board.remove(board.size() - 1);
            
        }
    }
}
