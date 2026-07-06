func longestCommonSubsequence(a string, b string) int {
    m := len(a)
    n := len(b)

    memo := make([][]int, m)

    for i := range memo {
        memo[i] = make([]int, n)
        for j := range memo[i] {
            memo[i][j] = -1
        }
    }

    return lcsCommonHelper(0, 0, a, b, memo);
}

func lcsCommonHelper(i int, j int, a string, b string, memo[][]int) int {
    if (i >= len(a) || j >= len(b)) {
        return 0;
    }

    if memo[i][j] != -1 {
        return memo[i][j]
    }
    
    if (a[i] == b[j]) {
        memo[i][j] = 1 + lcsCommonHelper(i + 1, j + 1, a, b, memo)
        return memo[i][j]
    }

    skipA := lcsCommonHelper(i + 1, j, a, b, memo);
    skipB := lcsCommonHelper(i, j + 1, a, b, memo);

    memo[i][j] = max(skipA, skipB);

    return memo[i][j]
} 
