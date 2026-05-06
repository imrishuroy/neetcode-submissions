class Solution {
    List<List<String>> result;
    boolean[] col;    // tracks used columns
    boolean[] diag1;  // main diagonals (r - c)
    boolean[] diag2;  // anti diagolans (r + c)
    char[][] board;

    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        col = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];
        board = new char[n][n];

        // initialize board
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(0, n);
        return result;
    }

    private void backtrack(int row, int n) {
        // base case
        // if all rows filled, we found a valid arrangement
        if (row == n) {
            List<String> copy = new ArrayList<>();
            for (char[] r: board){
                copy.add(new String(r));
            }
            result.add(copy);
            return;
        }

        // try all choices for current row
        for (int c = 0; c < n; c++) {
            int d1 = row - c + n; // shift index to stay positive
            int d2 = row + c;

            // if columns or diagonals already used -> skip
            if (col[c] || diag1[d1] || diag2[d2]) continue;

            // make choice
            board[row][c] = 'Q';
            col[c] = diag1[d1] = diag2[d2] = true;

            // explore next row
            backtrack(row + 1, n);

            // undo choice (backtrack)
            board[row][c] = '.';
            col[c] = diag1[d1] = diag2[d2] = false;
        }
    }
}