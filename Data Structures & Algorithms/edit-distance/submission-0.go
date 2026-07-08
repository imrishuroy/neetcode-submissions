func minDistance(word1 string, word2 string) int {
	n := len(word1)
	m := len(word2)

	memo := make([][]int, n)

	for i := range memo {
		memo[i] = make([]int, m)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

    return minDistanceHelper(0, 0, word1, word2, memo);
}

func minDistanceHelper(i int, j int, word1 string, word2 string, memo [][]int) int{
	// base 
	
	if i == len(word1) { // insert all the remaining characters
		return len(word2) - j
	} 

	if j == len(word2) { // delete all the remaining characters
		return len(word1) - i
	}
	
	if memo[i][j] != -1 {
		return memo[i][j]
	}

	if word1[i] == word2[j] {
		return minDistanceHelper(i + 1, j + 1, word1, word2, memo)
	}

	insertCh := minDistanceHelper(i, j + 1, word1, word2, memo)
	deleteCh := minDistanceHelper(i + 1, j, word1, word2, memo)
	replaceCh := minDistanceHelper(i + 1, j + 1, word1, word2, memo)

	memo[i][j] = 1 + min(insertCh, min(deleteCh, replaceCh))

	return memo[i][j]
}
