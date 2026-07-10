func maxProfit(prices []int) int {
	n := len(prices)

	memo := make([][]int, n)
	for i := range memo {
		memo[i] = make([]int, 2)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

    return maxProfitHelper(0, 1, prices, memo)
}

func maxProfitHelper(i int, canBuy int, prices []int, memo [][]int) int{
	// base
	if i >= len(prices) {
		return 0
	}

	if memo[i][canBuy] != -1 {
		return memo[i][canBuy]
	}

	var profit int

	if canBuy == 1 { // to buy the stock you have to give money means subtract the current stock price with the existing money you have
		buy := maxProfitHelper(i + 1, 0, prices, memo) - prices[i]
		hold := maxProfitHelper(i + 1, 1, prices, memo)

		profit = max(buy, hold)
	} else {
		sell := maxProfitHelper(i + 2, 1, prices, memo) + prices[i]
		hold := maxProfitHelper(i + 1, 0, prices, memo)

		profit = max(sell, hold)
	}

	memo[i][canBuy] = profit

	return profit
}