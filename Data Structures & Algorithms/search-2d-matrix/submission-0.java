class Solution {

    // 1. every row is sorted
    // 2. the first integer of every row is greater than the last integer of the previous row
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(matrix[i][j]);
                if (matrix[i][j] == target) return true;
            }
        }

        return false;
    }
}
