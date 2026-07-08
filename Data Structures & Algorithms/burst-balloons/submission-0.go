/*
Intuition:
----------
Bursting balloons from left to right is difficult because every burst changes the
neighbors of the remaining balloons, making the state unpredictable.

Instead, we reverse our thinking.

Rather than deciding which balloon to burst FIRST, we decide which balloon is
burst LAST within a given interval.

If balloon 'k' is the last one burst between (left, right), then all other
balloons inside that interval have already been removed.

Therefore, when bursting balloon 'k', its neighbors are guaranteed to be
'left' and 'right', allowing us to calculate the coins directly as:

    nums[left] * nums[k] * nums[right]

This eliminates the changing-neighbor problem and naturally leads to an
Interval Dynamic Programming solution.

Approach:
---------
1. Add two virtual balloons with value 1 at both ends of the array.
   This avoids handling edge cases when bursting the first or last balloon.

2. Define:
      dfs(left, right)

   = Maximum coins obtainable by bursting every balloon strictly between
     indices left and right.

   Note:
   Balloons at 'left' and 'right' are NOT burst in this recursive call.
   They only serve as fixed boundaries.

3. Try every balloon between (left, right) as the LAST balloon to burst.

   Coins earned =
        Coins from left interval
      + Coins from right interval
      + Coins earned by bursting the last balloon.

4. Since many intervals repeat, use memoization to store previously computed
   results and avoid redundant recursion.

Time Complexity:
----------------
There are O(n²) unique intervals.

For each interval, we try every possible last balloon,
which takes O(n).

Overall Time Complexity:
O(n³)

Space Complexity:
-----------------
Memoization table:
O(n²)

Recursion stack:
O(n)

Overall Space Complexity:
O(n²)
*/

func maxCoins(nums []int) int {

	// Add virtual balloons with value 1 at both ends.
	// This ensures every balloon always has valid left and right neighbors.
	arr := make([]int, 0, len(nums)+2)
	arr = append(arr, 1)
	arr = append(arr, nums...)
	arr = append(arr, 1)

	n := len(arr)

	// memo[left][right] stores the maximum coins obtainable
	// by bursting all balloons between left and right.
	memo := make([][]int, n)
	for i := range memo {
		memo[i] = make([]int, n)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	// Solve for the entire interval.
	return dfs(0, n-1, arr, memo)
}

func dfs(left int, right int, nums []int, memo [][]int) int {

	// Base Case:
	// No balloons exist between left and right.
	if left+1 == right {
		return 0
	}

	// Return already computed answer.
	if memo[left][right] != -1 {
		return memo[left][right]
	}

	best := 0

	// Try every balloon as the LAST balloon to burst
	// within the current interval.
	for k := left + 1; k < right; k++ {

		// Burst all balloons on the left side first.
		leftCoins := dfs(left, k, nums, memo)

		// Burst all balloons on the right side.
		rightCoins := dfs(k, right, nums, memo)

		// Finally burst balloon 'k'.
		// Since it is the last balloon remaining in this interval,
		// its neighbors are exactly 'left' and 'right'.
		currentCoins := nums[left] * nums[k] * nums[right]

		totalCoins := leftCoins + rightCoins + currentCoins

		best = max(best, totalCoins)
	}

	// Cache the computed result.
	memo[left][right] = best

	return best
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}