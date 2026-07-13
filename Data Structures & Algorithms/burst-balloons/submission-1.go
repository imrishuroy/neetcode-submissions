// order maters
func maxCoins(nums []int) int {
   i := 0
   j := len(nums) - 1 

   memo := make([][]int, len(nums))
   for i := range memo {
		memo[i] = make([]int, len(nums))

		for j := range memo[i] {
			memo[i][j] = -1
		}
   }

   return maxCoinsHelper(i, j, nums, memo)
}

func maxCoinsHelper(i int, j int, nums[]int, memo [][]int) int{
	if i > j {
		return 0
	}

	if memo[i][j] != -1 {
		return memo[i][j]
	} 

	var leftBoundary int
	var rightBoundary int

	if i == 0 {
		leftBoundary = 1
	} else {
		leftBoundary = nums[i - 1]
	}

	if j == len(nums) - 1 {
		rightBoundary = 1
	} else {
		rightBoundary = nums[j + 1]
	}

	var maxCoin int

	for k := i; k <= j; k++ {
		left := maxCoinsHelper(i, k - 1, nums, memo)
		right := maxCoinsHelper(k + 1, j, nums, memo)

		coins := left + right + leftBoundary * nums[k] * rightBoundary

		maxCoin = max(maxCoin, coins)
	}

	memo[i][j] = maxCoin
	return maxCoin
}
