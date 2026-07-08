/*
Intuition:
----------
We process both the input string and the pattern from left to right.

At every position (i, j), we ask:
    "Can the remaining substring s[i:] match the remaining pattern p[j:]?"

The tricky part is handling '*'.

If the next character in the pattern is '*', we have two choices:
1. Ignore the current character and '*' completely (zero occurrences).
2. If the current characters match, consume one character from the string
   while staying at the same pattern position so '*' can match more characters.

Since the same (i, j) state can be reached through many different recursive
paths, we memoize every state to avoid repeated work.

Approach:
---------
1. Define DFS(i, j):
      Returns whether s[i:] matches p[j:].

2. Base case:
      If the pattern is exhausted, the string must also be exhausted.

3. Check whether the current characters match:
      - Same character
      - OR pattern contains '.'

4. If the next pattern character is '*':
      - Skip the "x*" completely.
      - OR consume one matching character and stay on the same pattern.

5. Otherwise:
      - Match the current characters and move both pointers forward.

6. Memoize every (i, j) result so each state is solved only once.

Time Complexity:
----------------
There are (n + 1) × (m + 1) possible states.

Each state is computed only once.

Time: O(n × m)

Space Complexity:
-----------------
Memo table: O(n × m)

Recursion stack: O(n + m)

Overall: O(n × m)
*/

func isMatch(s string, p string) bool {
	n := len(s)
	m := len(p)

	// memo[i][j]
	// -1 -> not computed
	//  0 -> false
	//  1 -> true
	//
	// Size is (n+1) x (m+1) because i and j are allowed
	// to reach the end of the string/pattern.
	memo := make([][]int, n+1)

	for i := range memo {
		memo[i] = make([]int, m+1)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	return dfs(0, 0, s, p, memo)
}

func dfs(i int, j int, s string, p string, memo [][]int) bool {

	// If the pattern is completely consumed,
	// the string must also be completely consumed.
	if j == len(p) {
		return i == len(s)
	}

	// Return the cached answer if we've already solved
	// this (i, j) state.
	if memo[i][j] != -1 {
		return memo[i][j] == 1
	}

	// Check whether the current characters match.
	//
	// They match if:
	// 1. We're still inside the string.
	// 2. Characters are equal OR pattern contains '.'
	firstMatch := i < len(s) &&
		(s[i] == p[j] || p[j] == '.')

	var result bool

	// If the next pattern character is '*',
	// we have two possible choices.
	if j+1 < len(p) && p[j+1] == '*' {

		// Choice 1:
		// Skip the current character and '*'
		// Example:
		// s = "b"
		// p = "a*b"
		//
		// Skip "a*" completely.
		skipStar := dfs(i, j+2, s, p, memo)

		// Choice 2:
		// Use one occurrence of the current character.
		// Consume one character from the string while
		// staying on the same pattern because '*' can
		// still match more characters.
		useStar := firstMatch &&
			dfs(i+1, j, s, p, memo)

		result = skipStar || useStar

	} else {

		// Normal character (or '.')
		// Characters must match and both pointers move forward.
		result = firstMatch &&
			dfs(i+1, j+1, s, p, memo)
	}

	// Store the computed answer.
	if result {
		memo[i][j] = 1
	} else {
		memo[i][j] = 0
	}

	return result
}