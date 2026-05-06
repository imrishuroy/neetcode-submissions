
/*
====================================================
Problem: Pacific Atlantic Water Flow
====================================================

Given an m x n grid of heights, water can flow from a cell
to its 4-directional neighbor if the neighbor's height
is less than or equal to the current cell.

Pacific Ocean touches:
- Top row
- Left column

Atlantic Ocean touches:
- Bottom row
- Right column

We must find all cells from which water can flow
to BOTH the Pacific and Atlantic oceans.
*/

/*
====================================================
Intuition
====================================================

A direct simulation from every cell to oceans would be expensive.

Instead, we reverse the flow:

- Start DFS from the ocean borders.
- Move only to cells with height >= current cell.
  (This means we are moving "uphill" in reverse flow)

If an ocean can reach a cell in this reversed traversal,
then that cell can send water to that ocean in real flow.

Finally:
Cells reachable from BOTH oceans are our answer.
*/

/*
====================================================
Approach
====================================================

1. Create two visited matrices:
   pac[r][c] → reachable from Pacific
   atl[r][c] → reachable from Atlantic

2. Run DFS from all Pacific border cells:
   - Top row
   - Left column

3. Run DFS from all Atlantic border cells:
   - Bottom row
   - Right column

4. During DFS, move only if:
   - Next cell is inside grid
   - Not visited already
   - Next cell height >= current cell height

5. After DFS completes:
   Any cell marked in both pac and atl is added to result.
*/

/*
====================================================
Time Complexity
====================================================
Each cell is visited at most once for Pacific DFS
and once for Atlantic DFS.

Total Time: O(m * n)

====================================================
Space Complexity
====================================================
Visited arrays: O(m * n)
Recursion stack (worst case): O(m * n)

Total Space: O(m * n)
*/

class Solution {

    // Directions: down, up, right, left
    private int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        // pac[r][c] = reachable from Pacific Ocean
        // atl[r][c] = reachable from Atlantic Ocean
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];

        // Start DFS from Pacific (top row) and Atlantic (bottom row)
        for (int c = 0; c < cols; c++) {
            dfs(0, c, pac, heights);           // Pacific top border
            dfs(rows - 1, c, atl, heights);    // Atlantic bottom border
        }

        // Start DFS from Pacific (left col) and Atlantic (right col)
        for (int r = 0; r < rows; r++) {
            dfs(r, 0, pac, heights);           // Pacific left border
            dfs(r, cols - 1, atl, heights);    // Atlantic right border
        }

        // Collect cells reachable from both oceans
        List<List<Integer>> result = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pac[r][c] && atl[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    /*
    DFS marks all cells reachable from an ocean border.
    We move only "uphill" (neighbor height >= current height).
    */
    private void dfs(int r, int c, boolean[][] ocean, int[][] heights) {

        // Mark current cell as reachable from this ocean
        ocean[r][c] = true;

        // Explore all 4 directions
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];

            // Move only if:
            // - inside grid
            // - not visited before
            // - neighbor height >= current height
            if (nr >= 0 && nr < heights.length &&
                nc >= 0 && nc < heights[0].length &&
                !ocean[nr][nc] &&
                heights[nr][nc] >= heights[r][c]) {

                dfs(nr, nc, ocean, heights);
            }
        }
    }
}
