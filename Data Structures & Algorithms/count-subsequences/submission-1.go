func numDistinct(s string, t string) int {

	n := len(s)
	m := len(t)

	memo := make([][]int, n)
	for i := range memo {
		memo[i] = make([]int, m)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}


    return numDistinctHelper(0, 0, s, t, memo)
}

func numDistinctHelper(i int, j int, s string, t string, memo [][]int) int{
	// base case
	if (j == len(t)) {
		return 1
	}

	if i == len(s) {
		return 0
	}

	if memo[i][j] != -1 {
		return memo[i][j]
	}

	var take int

	if s[i] == t[j] {
		take = numDistinctHelper(i + 1, j + 1, s, t, memo)
	}

	skip := numDistinctHelper(i + 1, j, s, t, memo)
	
	memo[i][j] = take + skip
	return memo[i][j]
}
