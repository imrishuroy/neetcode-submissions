/*
Intuition:
----------
We want to convert word1 into word2 using the minimum number of operations.

At every position (i, j), we compare:
    word1[i] and word2[j]

There are only two possibilities:

1. Characters are the same.
   - No operation is required.
   - Simply move to the next character in both strings.

2. Characters are different.
   - We have exactly three possible operations:
        a) Insert
        b) Delete
        c) Replace

Since we don't know which operation leads to the optimal answer,
we recursively try all three possibilities and choose the one
that produces the minimum number of operations.

Many (i, j) states are visited repeatedly, so we store the answer
for every state using memoization.

State:
------
(i, j)
Minimum operations required to convert
word1[i...] -> word2[j...]

Approach:
---------
1. Use recursion starting from (0,0).
2. If characters match:
      Skip both characters.
3. Otherwise:
      Try
        - Insert
        - Delete
        - Replace
      and take the minimum.
4. Store every computed state in memo.
5. Return the answer stored for (0,0).

Operations Explained:
---------------------
Insert:
Suppose word1 = "abc"
        word2 = "axbc"

Insert 'x' into word1.

word1 index stays at i because the current character
still needs to be matched.

Move only in word2.

(i, j) -> (i, j+1)

Delete:
Suppose word1 = "axbc"
        word2 = "abc"

Delete 'x' from word1.

Move only in word1.

(i, j) -> (i+1, j)

Replace:
Suppose word1 = "abc"
        word2 = "adc"

Replace 'b' with 'd'.

Both characters are consumed.

(i, j) -> (i+1, j+1)

Time Complexity:
----------------
There are n * m unique states.

Each state is solved only once and performs O(1) work.

Time = O(n * m)

Space Complexity:
-----------------
Memoization table = O(n * m)

Recursive call stack = O(n + m)

Overall = O(n * m)
*/

func minDistance(word1 string, word2 string) int {
	n := len(word1)
	m := len(word2)

	// memo[i][j] stores the minimum operations required to convert
	// word1[i...] into word2[j...]
	memo := make([][]int, n)

	for i := range memo {
		memo[i] = make([]int, m)

		// -1 means this state has not been computed yet.
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	// Start comparing from the beginning of both strings.
	return minDistanceHelper(0, 0, word1, word2, memo)
}

func minDistanceHelper(i int, j int, word1 string, word2 string, memo [][]int) int {

	// Base Case 1:
	// We have consumed all characters of word1.
	//
	// The only way to make both strings equal now is to
	// insert every remaining character from word2.
	if i == len(word1) {
		return len(word2) - j
	}

	// Base Case 2:
	// We have consumed all characters of word2.
	//
	// The remaining characters in word1 must all be deleted.
	if j == len(word2) {
		return len(word1) - i
	}

	// If we have already solved this state,
	// simply return the stored answer.
	if memo[i][j] != -1 {
		return memo[i][j]
	}

	// If current characters are already equal,
	// no edit operation is needed.
	//
	// Move to the next character in both strings.
	if word1[i] == word2[j] {
		memo[i][j] = minDistanceHelper(i+1, j+1, word1, word2, memo)
		return memo[i][j]
	}

	// ----------------------------------------------------
	// Characters are different.
	// We must perform one of the three edit operations.
	// ----------------------------------------------------

	// Option 1: Insert
	//
	// Insert word2[j] into word1.
	//
	// Current character of word1 still needs to be matched,
	// so keep i unchanged and move only j.
	insertCh := minDistanceHelper(i, j+1, word1, word2, memo)

	// Option 2: Delete
	//
	// Delete word1[i].
	//
	// Move to the next character in word1,
	// while word2 stays at the same character.
	deleteCh := minDistanceHelper(i+1, j, word1, word2, memo)

	// Option 3: Replace
	//
	// Replace word1[i] with word2[j].
	//
	// Both characters are considered matched,
	// so move both pointers.
	replaceCh := minDistanceHelper(i+1, j+1, word1, word2, memo)

	// Count the current operation (+1)
	// and choose the best among the three possibilities.
	memo[i][j] = 1 + min(insertCh, min(deleteCh, replaceCh))

	return memo[i][j]
}