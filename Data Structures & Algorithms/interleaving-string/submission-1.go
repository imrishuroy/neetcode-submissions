func isInterleave(s1 string, s2 string, s3 string) bool {
    if len(s1) + len(s2) != len(s3) {
		return false
	}

	n := len(s1)
	m := len(s2)

	memo := make([][]int, n + 1)
	for i := range memo {
		memo[i] = make([]int, m + 1)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	return isInterleaveHelper(0, 0, s1, s2, s3, memo)
}

func isInterleaveHelper(i int, j int, s1 string, s2 string, s3 string, memo [][]int) bool{
	// base case
	k := i + j

	if i == len(s1) && j == len(s2) && k == len(s3) {
		return true
	}

	if memo[i][j] != -1 {
		return memo[i][j] == 1
	}

	var takeFromS1 bool 
	if i < len(s1) && s1[i] == s3[k] {
		takeFromS1 = isInterleaveHelper(i + 1, j, s1, s2, s3, memo)
	}

	var takeFromS2 bool
	if j < len(s2) && s2[j] == s3[k] {
		takeFromS2 = isInterleaveHelper(i, j + 1, s1, s2, s3, memo)
	}
	result := takeFromS1 || takeFromS2
	if result {
		memo[i][j] = 1
	} else {
		memo[i][j] = 0
	}

	return result
}