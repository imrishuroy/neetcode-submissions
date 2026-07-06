func longestCommonSubsequence(a string, b string) int {

    /*
       Intuition:
       ----------
       We need to find the length of the longest subsequence
       that appears in both strings.

       At every pair of indices (i, j), we have two possibilities:

       Case 1:
       If characters match,
           a[i] == b[j]

       then this character must be part of the LCS,
       so include it and move both pointers forward.

                  i
       a = "abcde"
                  j
       b = "ace"

       'a' == 'a'

       Answer = 1 + LCS(i+1, j+1)


       ----------------------------------------------------

       Case 2:
       Characters don't match.

       We don't know which character should be ignored.

       So we try both possibilities:

       Skip character from string A

           LCS(i+1, j)

       Skip character from string B

           LCS(i, j+1)

       Take whichever gives the larger answer.

       This creates overlapping subproblems,
       making it a Dynamic Programming problem.
    */

    m := len(a)
    n := len(b)

    /*
       memo[i][j]

       Stores the answer for:

       LCS between
       a[i...]
       b[j...]

       -1 means this state hasn't been computed yet.
    */
    memo := make([][]int, m)

    for i := range memo {
        memo[i] = make([]int, n)

        // Initialize every cell with -1
        // since Go initializes integers to 0 by default.
        for j := range memo[i] {
            memo[i][j] = -1
        }
    }

    return lcsCommonHelper(0, 0, a, b, memo)
}

func lcsCommonHelper(i int, j int, a string, b string, memo [][]int) int {

    /*
       Base Case

       If we've reached the end of either string,
       there are no more characters available
       to form a common subsequence.
    */
    if i >= len(a) || j >= len(b) {
        return 0
    }

    /*
       If we've already solved this state,
       return the stored answer instead of
       recomputing it.
    */
    if memo[i][j] != -1 {
        return memo[i][j]
    }

    /*
       Characters match.

       Include this character in the LCS
       and move both pointers forward.
    */
    if a[i] == b[j] {
        memo[i][j] = 1 + lcsCommonHelper(i+1, j+1, a, b, memo)
        return memo[i][j]
    }

    /*
       Characters don't match.

       Two possible choices:

       1. Skip current character from string A
       2. Skip current character from string B

       Take whichever produces the longer subsequence.
    */
    skipA := lcsCommonHelper(i+1, j, a, b, memo)
    skipB := lcsCommonHelper(i, j+1, a, b, memo)

    memo[i][j] = max(skipA, skipB)

    return memo[i][j]
}

// func max(a, b int) int {
//     if a > b {
//         return a
//     }
//     return b
// }

/*
Approach:
---------
1. Start comparing both strings from index (0, 0).

2. If characters match:
      include them in the answer
      and move both pointers.

3. Otherwise:
      try skipping one character from
      either string and choose the better result.

4. Since many states are repeated,
   store every computed answer inside memo.

5. Every state (i, j) is solved only once.

Time Complexity:
----------------
Number of states = m × n

Each state performs only constant work.

O(m × n)

Space Complexity:
-----------------
Memo table : O(m × n)

Recursion stack:
O(m + n)

Overall:
O(m × n)
*/