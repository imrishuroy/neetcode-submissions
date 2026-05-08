class Solution {
    public boolean isValidSudoku(char[][] board) {

        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<String, Set<Character>> squares = new HashMap<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {

                String key = (r / 3) + "," + (c / 3); 

                rows.putIfAbsent(r, new HashSet<>());
                cols.putIfAbsent(c, new HashSet<>());
                squares.putIfAbsent(key, new HashSet<>());

                char num = board[r][c];
                if (num == '.') continue;

                // checking duplicates
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
    

                // 3 * 3 box
                // 0, 0 | 0, 1 | 0, 2
                // 1, 0 | 1, 1 | 1, 2
                // 2, 0 | 2, 1,| 2, 2
            }
        }
        return true;
    }
}
