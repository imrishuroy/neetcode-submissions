class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        /*
         Intuition:
         ----------
         The matrix is sorted in a special way:
         
         1. Each row is sorted.
         2. First element of every row is greater than
            the last element of previous row.

         Example:
         1  3  5
         7  9 11
         13 15 17

         If we read row by row, it becomes:
         [1, 3, 5, 7, 9, 11, 13, 15, 17]

         So the entire matrix behaves like ONE sorted array.

         Since binary search works on sorted arrays,
         we can apply binary search here as well.

         -------------------------------------------------

         Approach:
         ----------
         Instead of actually creating a new 1D array,
         we "pretend" the matrix is a single array.

         Total elements = rows * cols

         Index mapping:
         --------------
         Suppose matrix has:
         n = number of columns

         Then for any imaginary 1D index:

             row = index / n
             col = index % n

         Why?

         Example:
         --------
         Matrix:

         1  3  5
         7  9 11
         13 15 17

         n = 3 columns

         Imaginary 1D indexes:

         index -> value

         0 -> 1
         1 -> 3
         2 -> 5
         3 -> 7
         4 -> 9
         5 -> 11
         6 -> 13
         7 -> 15
         8 -> 17

         Now see mapping:

         index = 5

         row = 5 / 3 = 1
         col = 5 % 3 = 2

         matrix[1][2] = 11

         --------------------------------------------

         Why division gives row?
         -----------------------
         Every row contains exactly 'n' elements.

         So:
         indexes 0,1,2 belong to row 0
         indexes 3,4,5 belong to row 1
         indexes 6,7,8 belong to row 2

         Integer division tells how many full rows
         are completed before this index.

         --------------------------------------------

         Why modulo (%) gives column?
         ----------------------------
         % gives the remainder.

         Example:
         5 % 3 = 2

         Meaning:
         after filling complete rows,
         position inside current row is column 2.
        */

        int m = matrix.length;
        int n = matrix[0].length;

        // Total elements in matrix
        int len = m * n;

        // Binary search on imaginary sorted array
        int left = 0;
        int right = len - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // Convert 1D index -> 2D coordinates
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target) {
                return true;
            }

            // Search right half
            else if (matrix[row][col] < target) {
                left = mid + 1;
            }

            // Search left half
            else {
                right = mid - 1;
            }
        }

        return false;
    }
}