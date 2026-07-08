
var directions = [][2]int{ {0, 1}, {1, 0}, {0, -1}, {-1, 0} }

func longestIncreasingPath(matrix [][]int) int {

	n := len(matrix)
	m := len(matrix[0])

	longest := 0

	visited := make([][]bool, n)
	for i := range visited {
		visited[i] = make([]bool, m)
	}

	memo := make([][]int, n)
	for i := range memo {
		memo[i] = make([]int, m) 

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			longest = max(longest, dfs(i, j, matrix, visited, memo))
		}
	}

	return longest
    
}

func dfs(i int, j int, matrix [][]int, visited [][]bool, memo [][]int) int{
	if visited[i][j] {
		return 0
	}

	if memo[i][j] != -1 {
		return memo[i][j]
	}

	visited[i][j] = true

	longest := 1

	for _, dir := range directions {
		newR := i + dir[0]
		newC := j + dir[1]

		if newR >= 0 && newR < len(matrix) &&
			newC >= 0 && newC < len(matrix[0]) &&
			!visited[newR][newC] &&
			matrix[newR][newC] > matrix[i][j] {

			longest = max(longest, 1 + dfs(newR, newC, matrix, visited, memo))
		}

	}
	
	visited[i][j] = false
	memo[i][j] = longest
	return longest
}