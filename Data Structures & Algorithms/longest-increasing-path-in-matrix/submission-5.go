var directions = [][2]int{
	{0, 1},
	{1, 0},
	{0, -1},
	{-1, 0},
}

func longestIncreasingPath(matrix [][]int) int {
    m := len(matrix)
	n := len(matrix[0])

	// visited := make([][]bool, m)
	// for i := range visited {
	// 	visited[i] = make([]bool, n)
	// }

	memo := make([][]int, m)
	for i := range memo {
		memo[i] = make([]int, n)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	longest := 0

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			longest = max(longest, dfs(i, j, matrix, memo))
		}
	}

	return longest
}

func dfs(i int, j int, matrix [][]int, memo[][]int) int{

	if (memo[i][j] != -1) {
		return memo[i][j]
	}

	best := 1

	for _, dir := range directions {
		nRow := i + dir[0]
		nCol := j + dir[1]

		if (nRow >= 0 && nRow < len(matrix) && 
			nCol >= 0 && nCol < len(matrix[0]) &&
			matrix[nRow][nCol] > matrix[i][j]) {
				best = max(best, 1 + dfs(nRow, nCol, matrix, memo))
			}
	}
	
	memo[i][j] = best
	return best
}