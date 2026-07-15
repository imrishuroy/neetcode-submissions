/*

LeetCode 10. Regular Expression Matching

Regex Rules:
1. '.' matches any single character.
2. '*' matches zero or more occurrences of the character before it.

Examples:
s = "aa", p = "a*"     -> true
s = "ab", p = ".*"     -> true
s = "aab", p = "c*a*b" -> true

-------------------------------------------------------
INTUITION
-------------------------------------------------------

At every position (i, j), we ask a simple question:

Can the remaining string s[i:] match the remaining pattern p[j:] ?

From every state there are only two possibilities.

Case 1: Next character in pattern is NOT '*'

Example:
s = "abc"
     ^
p = "abc"
     ^

If current characters match, both pointers move forward.

    dfs(i + 1, j + 1)

Otherwise the answer is false.


-------------------------------------------------------

Case 2: Next character IS '*'

Example:

s = "aaa"
     ^
p = "a*"
     ^

The '*' gives us TWO choices.

Choice 1 -> Skip "a*"

Treat "a*" as matching ZERO characters.

s = "aaa"
     ^
p = "a*"
       ^

Move pattern pointer by 2.

        dfs(i, j + 2)

-------------------------------------------------------

Choice 2 -> Take one character

We use '*' to consume one matching character.

This is ONLY possible if current characters match.

s = "aaa"
      ^
p = "a*"
     ^

Notice:

String pointer moves.

Pattern pointer DOES NOT move.

Because '*' may still match more characters.

        dfs(i + 1, j)

-------------------------------------------------------

Decision Tree

                 a*
               /     \
          Skip        Take
       dfs(i,j+2)  dfs(i+1,j)

Answer = Skip || Take

-------------------------------------------------------
WHY DOES TAKE KEEP j SAME?

Pattern:

a*

Suppose

s = "aaaa"

After consuming ONE 'a'

Remaining string

aaa

Pattern is STILL

a*

because '*' can match unlimited characters.

Only when we decide to stop consuming do we skip over "a*".

-------------------------------------------------------
APPROACH
-------------------------------------------------------

State

dfs(i, j)

Meaning:

Can s[i:] match p[j:] ?

Base Case

If pattern is exhausted,

answer is true only if string is also exhausted.

        j == len(p)

-------------------------------------------------------

Transition

1. Check if current characters match.

2. If next character is '*'

        skip = dfs(i, j + 2)

        take = false

        if current characters match
                take = dfs(i + 1, j)

        return skip || take

3. Otherwise

        if characters match
                return dfs(i + 1, j + 1)

        return false

-------------------------------------------------------
TIME COMPLEXITY

Without Memoization

Each '*' creates two recursive branches.

Worst Case

s = "aaaaaaaaaaaa"

p = "a*a*a*a*a*a*a*"

This explores many repeated states.

Time Complexity

O(2^(n + m))

-------------------------------------------------------

SPACE COMPLEXITY

Recursive stack depth

Maximum recursion depth is

O(n + m)

because every recursive call moves
either i or j forward.

(No extra data structures are used.)

===========================================
*/

func isMatch(s string, p string) bool {

	n := len(s)
	m := len(p)

	memo := make([][]int, n + 1)

	for i := range memo {
		memo[i] = make([]int, m + 1)

		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	return isMatchHelper(0, 0, s, p, memo)
}

func isMatchHelper(i, j int, s, p string, memo [][]int) bool {

	// -------------------------------------------------
	// Base Case
	//
	// If pattern is completely consumed,
	// string must also be completely consumed.
	// -------------------------------------------------
	if j == len(p) {
		return i == len(s)
	}

	if memo[i][j] != -1 {
		return memo[i][j] == 1
	}

	// -------------------------------------------------
	// Check whether current characters match.
	//
	// i < len(s)
	// prevents index out of bounds.
	//
	// '.' matches every character.
	// -------------------------------------------------
	match := i < len(s) &&
		(s[i] == p[j] || p[j] == '.')

	// -------------------------------------------------
	// Case:
	// Next pattern character is '*'
	//
	// Pattern looks like:
	//
	//      x*
	// -------------------------------------------------
	if j+1 < len(p) && p[j+1] == '*' {

		// ---------------------------------------------
		// Choice 1
		//
		// Skip x*
		//
		// Example
		//
		// s = "bbb"
		// p = "a*bbb"
		//
		// Ignore "a*" completely.
		// ---------------------------------------------
		skip := isMatchHelper(i, j+2, s, p, memo)

		// ---------------------------------------------
		// Choice 2
		//
		// Use '*'
		//
		// Only possible if current characters match.
		//
		// Move string pointer.
		//
		// Keep pattern pointer.
		//
		// Because '*' can still match
		// additional characters.
		// ---------------------------------------------
		var take bool

		if match {
			take = isMatchHelper(i+1, j, s, p, memo)
		}

		// If either choice succeeds,
		// overall answer is true.
		result := skip || take
		if result {
			memo[i][j] = 1
		} else {
			memo[i][j] = 0
		}
		return result
	}

	// -------------------------------------------------
	// Normal character
	//
	// No '*'
	//
	// Characters must match,
	// then move both pointers.
	// -------------------------------------------------
	var take bool

	if match {
		take = isMatchHelper(i+1, j+1, s, p, memo)
	}

	if take {
		memo[i][j] = 1
	} else {
		memo[i][j] = 0
	}

	return take
}