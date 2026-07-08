
var directions = [][2]int{ {0, 1}, {1, 0}, {0, -1}, {-1, 0} }

func longestIncreasingPath(matrix [][]int) int {

	n := len(matrix)
	m := len(matrix[0])

	longest := 0

	memo := make([][]int, n)
	for i := range memo {
		memo[i] = make([]int, m) 

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			longest = max(longest, dfs(i, j, matrix, memo))
		}
	}

	return longest
    
}

func dfs(i int, j int, matrix [][]int, memo [][]int) int{

	if memo[i][j] != -1 {
		return memo[i][j]
	}

	longest := 1

	for _, dir := range directions {
		newR := i + dir[0]
		newC := j + dir[1]

		if newR >= 0 && newR < len(matrix) &&
			newC >= 0 && newC < len(matrix[0]) &&
			matrix[newR][newC] > matrix[i][j] {

			longest = max(longest, 1 + dfs(newR, newC, matrix, memo))
		}

	}
	
	memo[i][j] = longest
	return longest
}