func maxProfit(prices []int) int {

	// state : buying or selling ?
	// if buy -> i + 1
	// if sell -> i + 2

    n := len(prices)
    memo := make([][]int, n)

    for i := range memo {
        memo[i] = make([]int, 2)

        for j := range memo[i] {
            memo[i][j] = -1
        }
    }

    // 1 -> true (can buy)
    // 0 -> false (can't buy)

	return maxProfitHelper(0, 1, prices, memo);
    
}

func maxProfitHelper(i int, canBuy int, prices []int, memo[][]int) int {
	if i >= len(prices) {
		return 0;
	}

    if memo[i][canBuy] != -1 {
        return memo[i][canBuy]
    }

	var profit int;

	if canBuy == 1 {
		buy := maxProfitHelper(i + 1, 0, prices, memo) - prices[i];
		cooldown := maxProfitHelper(i + 1, 1, prices, memo);
		profit = max(buy, cooldown);
	} else {
		sell := maxProfitHelper(i + 2, 1, prices, memo) + prices[i];
		cooldown := maxProfitHelper(i + 1, 0, prices, memo);
		profit = max(sell, cooldown);
	}

    memo[i][canBuy] = profit;
	return profit;

}
