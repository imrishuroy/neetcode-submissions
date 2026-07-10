type State struct {
	Index int
	Sum int
}

func findTargetSumWays(nums []int, target int) int {

	memo := make(map[State]int)

    return findTargetSumWaysHelper(0, 0, target, nums, memo)
}

func findTargetSumWaysHelper(i int, currSum int, target int, nums []int, memo map[State]int) int{
	if i == len(nums) && currSum == target {
		return 1
	}

	if i >= len(nums) {
		return 0
	}

	state := State {i, currSum}

	if value, exists := memo[state]; exists {
		return value
	}

	sum := findTargetSumWaysHelper(i + 1, currSum + nums[i], target, nums, memo)
	subs := findTargetSumWaysHelper(i + 1, currSum - nums[i], target, nums, memo)

	memo[state] = sum + subs
	return sum + subs
}
