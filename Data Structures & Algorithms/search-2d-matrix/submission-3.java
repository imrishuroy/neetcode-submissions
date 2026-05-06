class Solution {

    // 1. every row is sorted
    // 2. the first integer of every row is greater than the last integer of the previous row
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // System.out.println(m);

        for (int i = 0; i < m; i++) {
            
                if (target <= matrix[i][n - 1]) {

                    int left = 0;
                    int right = n - 1;

                    while (left <= right) {
                        int mid = (left + right) / 2;
                        if (matrix[i][mid] == target) {
                            return true;
                        } else if (matrix[i][mid] > target) {
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    }
                    
                }
        }

        return false;
    }


}


