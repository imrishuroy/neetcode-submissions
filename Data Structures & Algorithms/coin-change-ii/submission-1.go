func change(amount int, coins []int) int {
	n := len(coins)

	memo := make([][]int, n)
	for i := range memo {
		memo[i] = make([]int, amount + 1)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

    return coinChangeHelper(0, amount, coins, memo)
}

func coinChangeHelper(i int, amount int, coins []int, memo [][]int) int{
	// base case
	if amount == 0 {
		return 1
	}

	if i >= len(coins) || amount < 0 {
		return 0
	}

	if memo[i][amount] != -1 {
		return memo[i][amount]
	}

	var take = 0
	if coins[i] <= amount {
		take = coinChangeHelper(i, amount - coins[i], coins, memo)
	}

	skip := coinChangeHelper(i + 1, amount, coins, memo)

	memo[i][amount] = take + skip
	return take + skip
}
