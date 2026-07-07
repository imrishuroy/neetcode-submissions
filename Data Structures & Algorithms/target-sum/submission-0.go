type State struct{
	Index int
	Sum int
}

func findTargetSumWays(nums []int, target int) int {

	memo := make(map[State]int)
	
    return findTargetSumWaysHelper(0, nums, 0, target, memo);
}

func findTargetSumWaysHelper(i int, nums []int, currSum int, target int, memo map[State]int) int {
	if i == len(nums) {
		if currSum == target {
			return 1
		}
		return 0
	}

	state := State{i, currSum}

	if value, exists := memo[state]; exists {
		return value
	}

	add := findTargetSumWaysHelper(i + 1, nums, currSum + nums[i], target, memo)

	sub := findTargetSumWaysHelper(i + 1, nums, currSum - nums[i], target, memo)

	memo[state] = add + sub

	return memo[state]
}
