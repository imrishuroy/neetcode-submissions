

// Directions:
// Right, Down, Left, Up
var directions = [][2]int{
	{0, 1},
	{1, 0},
	{0, -1},
	{-1, 0},
}

/*
Approach:
---------
The longest increasing path can start from ANY cell in the matrix.

Therefore,

For every cell:
    - Start a DFS.
    - Find the longest increasing path beginning from that cell.
    - Keep track of the maximum answer.

To avoid solving the same subproblem repeatedly,
we use memoization.

------------------------------------------------------------

Intuition:
----------

Imagine every cell asks the following question:

    "If I start my journey from this cell,
     what is the longest increasing path I can make?"

Example:

    9  9  4
    6  6  8
    2  1  1

Suppose we start from value 1.

Possible path:

    1 -> 2 -> 6 -> 9

Length = 4

Now imagine another DFS also reaches value 2.

Should we calculate the answer from 2 again?

No.

The longest path starting from 2 will ALWAYS be the same,
regardless of how we reached that cell.

So once we calculate

    dfs(2)

we store it.

Every future DFS simply returns the stored answer.

------------------------------------------------------------

Why is this Dynamic Programming?
--------------------------------

At first glance this looks like a graph problem.

Each cell is a node.

Each increasing move is an edge.

However, pure DFS repeatedly solves the same subproblems.

Example:

        1
       / \
      2   3
       \ /
        5

Both paths eventually call

    dfs(5)

Without memoization:

    dfs(5)
    dfs(5)

is executed multiple times.

With memoization:

memo[5]

stores the answer.

Future calls immediately return the stored value.

Whenever:

    1. Overlapping subproblems exist
    2. The answer of a subproblem never changes

Dynamic Programming can be applied.

This solution is therefore

    DFS + Memoization

which is Top-Down Dynamic Programming.

------------------------------------------------------------

DP State:
---------

memo[row][col]

represents

"The length of the longest increasing path
starting from (row, col)."

It does NOT represent

- longest path ending here
- global answer

Only the answer starting from this cell.

------------------------------------------------------------

DFS Flow:
---------

DFS(row, col)

1. If answer already exists in memo,
   return it.

2. Assume current cell alone forms a path
   of length 1.

3. Visit all four directions.

4. Move only if

    - inside matrix
    - next value > current value

5. Compute

    1 + dfs(next)

6. Keep the maximum.

7. Store answer in memo.

8. Return answer.

------------------------------------------------------------

Time Complexity:
----------------

Rows = m
Columns = n

Each cell's DFS is computed ONLY ONCE.

Each DFS checks only 4 neighbors.

Time:

O(m × n)

------------------------------------------------------------

Space Complexity:
-----------------

Memo table:

O(m × n)

Recursion stack:

Worst case:

O(m × n)

(for a snake-like increasing path)

Overall:

O(m × n)

*/

func longestIncreasingPath(matrix [][]int) int {

	// Edge case
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return 0
	}

	rows := len(matrix)
	cols := len(matrix[0])

	// memo[r][c] stores the longest increasing
	// path starting from (r,c)
	memo := make([][]int, rows)

	for i := range memo {
		memo[i] = make([]int, cols)

		// -1 means answer has not been computed yet
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	longest := 0

	// Try every cell as a starting point.
	for r := 0; r < rows; r++ {
		for c := 0; c < cols; c++ {

			// Compute longest path starting
			// from this cell.
			longest = max(longest, dfs(r, c, matrix, memo))
		}
	}

	return longest
}

// dfs returns the longest increasing path
// starting from (row,col)
func dfs(row, col int, matrix [][]int, memo [][]int) int {

	// If already computed,
	// simply reuse the answer.
	if memo[row][col] != -1 {
		return memo[row][col]
	}

	// At minimum,
	// current cell itself forms
	// a path of length 1.
	longest := 1

	// Try all four directions.
	for _, dir := range directions {

		newRow := row + dir[0]
		newCol := col + dir[1]

		// Check boundary
		if newRow < 0 ||
			newRow >= len(matrix) ||
			newCol < 0 ||
			newCol >= len(matrix[0]) {
			continue
		}

		// Only move to a strictly larger value.
		if matrix[newRow][newCol] <= matrix[row][col] {
			continue
		}

		// Explore this path.
		pathLength := 1 + dfs(newRow, newCol, matrix, memo)

		// Keep the best answer.
		longest = max(longest, pathLength)
	}

	// Store answer so future DFS
	// can reuse it.
	memo[row][col] = longest

	return longest
}