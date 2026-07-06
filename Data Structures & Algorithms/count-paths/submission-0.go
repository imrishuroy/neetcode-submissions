func uniquePaths(m int, n int) int {

    dp := make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }
    
    // fill all the 0 index row with 1, means 1 way
    for col := 0; col < n; col++ {
        dp[0][col] = 1;
    }

    for row := 0; row < m; row++ {
        dp[row][0] = 1;
    }

    for i := 1; i < m; i++ {
        for j := 1; j < n; j++ {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }

    return dp[m - 1][n - 1];
}