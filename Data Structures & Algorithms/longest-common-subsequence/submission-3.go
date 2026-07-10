func longestCommonSubsequence(text1 string, text2 string) int {
   n := len(text1)
   m := len(text2)

   memo := make([][]int, n)

   for i := range memo {
		memo[i] = make([]int, m)

		for j := range memo[i] {
			memo[i][j] = -1
		}
   }

   return lcsHelper(0, 0, text1, text2, memo)
}

func lcsHelper(i int, j int, text1 string, text2 string, memo [][]int) int {
	if i == len(text1) || j == len(text2)  {
		return 0
	}

	if memo[i][j] != -1 {
		return memo[i][j]
	}

	if text1[i] == text2[j] {
		result := 1 + lcsHelper(i + 1, j + 1, text1, text2, memo)
		memo[i][j] = result
		return result
	}

	skipText1 := lcsHelper(i + 1, j, text1, text2, memo)
	skipText2 := lcsHelper(i, j + 1, text1, text2, memo)

	memo[i][j] = max(skipText1, skipText2)

	return memo[i][j]
}
