func uniquePaths(m int, n int) int {
    matrix := make([][]int, m)

	for i := range matrix {
		matrix[i] = make([]int, n)
	}

	// fill all the 0th col with 1, means 1 way
	for row := 0; row < m; row++ {
		matrix[row][0] = 1
	}

	// fill all the 0th rows with 1 means 1 way
	for col := 0; col < n; col++ {
		matrix[0][col] = 1
	}

	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1]
		}
	}

	return matrix[m - 1][n - 1]
}