class Solution {
    public boolean isValidSudoku(char[][] board) {

        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<String, Set<Character>> squares = new HashMap<>();
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char num = board[r][c];

                if (board[r][c] == '.') {
                    continue;
                }
                String key = (r / 3) + "," + (c / 3);

                rows.putIfAbsent(r, new HashSet<>());
                cols.putIfAbsent(c, new HashSet<>());
                squares.putIfAbsent(key, new HashSet<>());

                if (rows.get(r).contains(num)) {
                    return false;
                }

                if (cols.get(c).contains(num)) {
                    return false;
                }

                if (squares.get(key).contains(num)) {
                    return false;
                }

                rows.get(r).add(num);
                cols.get(c).add(num);
                squares.get(key).add(num);
            }
        }

        return true;
    }
}
