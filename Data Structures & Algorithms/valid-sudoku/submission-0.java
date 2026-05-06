class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<String, Set<Character>> squares = new HashMap<>();

    
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') continue;

                char num =  board[r][c];
                String squareKey = (r / 3) + "," + (c / 3);

                cols.putIfAbsent(c, new HashSet<>());
                rows.putIfAbsent(r, new HashSet<>());
                squares.putIfAbsent(squareKey, new HashSet<>());

                // checking duplicates
                if (cols.get(c).contains(num)) return false;
                if (rows.get(r).contains(num)) return false;
                if (squares.get(squareKey).contains(num)) return false;

                // add number to row, column, and square
                rows.get(r).add(num);
                cols.get(c).add(num);
                squares.get(squareKey).add(num);
            }
        }

        return true;
    }
}
