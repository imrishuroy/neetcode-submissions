class Solution {

    List<List<String>> result;
    Set<Integer> cols;
    Set<Integer> diag1; // main diagonals (r - c)
    Set<Integer> diag2; // anti digagonals (r + c)
    char[][] board;

    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        cols = new HashSet<>();
        diag1 = new HashSet<>();
        diag2 = new HashSet<>();
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(n, 0);

        return result;
    }

    private void backtrack(int n, int row) {
        // base case
        if (row == n) {
            List<String> copy = new ArrayList<>();
            // we are converting the char array to string
            for (char[] r : board) {
                copy.add(new String(r));
            }
            result.add(copy);
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row - col;
            int d2 = row + col;

            if (cols.contains(col) || diag1.contains(d1) || diag2.contains(d2)) {
                continue;
            }            

            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);

            board[row][col] = 'Q';

            backtrack(n, row + 1);
            
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
            board[row][col] = '.';
        }
    }
}
