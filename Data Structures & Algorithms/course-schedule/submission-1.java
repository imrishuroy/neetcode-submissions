class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        Map<Integer, List<Integer>> preMap = new HashMap();
        int[] state = new int[numCourses]; // 0 -> unvisited, 1 -> visiting -> 2 -> visited

        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }

        for (int[] prerequisite: prerequisites) {
            int u = prerequisite[0];
            int v = prerequisite[1];

            preMap.get(v).add(u); // v -> u
        }


        for (int course = 0; course < numCourses; course++) {
            if (!dfs(course, preMap, state)) return false;
        }

        return true;

    }

    private boolean dfs(int course, Map<Integer, List<Integer>> preMap, int[] state) {
        if (state[course] == 1) return false;
        if (state[course] == 2) return true;

        state[course] = 1;

        for (int nei : preMap.get(course)) {
            if (!dfs(nei, preMap, state)) return false;
        }

        state[course] = 2;
        return true;
    }
    












    /*
     ============================================================
     COURSE SCHEDULE — CYCLE DETECTION USING DFS
     ============================================================

     WHY THIS IS A GRAPH PROBLEM:
     ----------------------------
     Each course can depend on another course.
     This forms a DIRECTED GRAPH:
       - Each course = a node
       - Each prerequisite [u, v] = directed edge v -> u
         (meaning: to take course u, you must first take v)

     The question "Can we finish all courses?" becomes:
       -> Can we traverse this directed graph without running into cycles?

     If a cycle exists:
         A -> B -> C -> A
     Then no course in the cycle can be started — impossible to finish.
     Therefore, we must detect whether the directed graph contains a cycle.

     ------------------------------------------------------------
     INTUITION:
     ------------------------------------------------------------
     We perform DFS on each course.

     While doing DFS, we track the courses currently in the recursion path.
     If during DFS we visit a course already in the current recursion path,
     it means we found a back-edge → a cycle → return false.

     If DFS completes without finding a cycle, return true.

     ------------------------------------------------------------
     ROLE OF 'visiting' SET (RECURSION STACK):
     ------------------------------------------------------------
     visiting = set of nodes in the CURRENT DFS path.

     When DFS enters a node:
       - If node already in visiting → cycle found.
       - Otherwise add node to visiting.

     When DFS leaves a node:
       - Remove node from visiting.
       - Mark node as fully processed.

     ------------------------------------------------------------
     OPTIMIZATION (MEMOIZATION):
     ------------------------------------------------------------
     Once a course is fully processed and found safe,
     we clear its adjacency list.

     Later, if DFS reaches this course again,
     an empty adjacency list means:
        -> already processed
        -> no need to DFS again
        -> safe node

     ------------------------------------------------------------
     ALGORITHM STEPS:
     ------------------------------------------------------------
     1. Build adjacency list for graph.
     2. For each course:
           Run DFS to detect cycle.
           If any DFS returns false → return false.
     3. If all courses pass DFS → return true.

     ------------------------------------------------------------
     COMPLEXITY:
     ------------------------------------------------------------
     Time:  O(V + E)
     Space: O(V + E)
     where V = number of courses, E = prerequisites
     ============================================================
    */

    public boolean canFinish2(int numCourses, int[][] prerequisites) {

        // Step 1: Build the graph as an adjacency list
        // adjList.get(v) contains list of courses that depend on v
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        // Initialize adjacency list
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }

        // Build directed edges: v -> u
        for (int[] prerequisite : prerequisites) {
            int u = prerequisite[0]; // course to take
            int v = prerequisite[1]; // prerequisite course
            adjList.get(v).add(u);
        }

        // visiting set represents the recursion stack (current DFS path)
        Set<Integer> visiting = new HashSet<>();

        // Step 2: Run DFS from every course
        // Graph may be disconnected, so we must check all nodes
        for (int course = 0; course < numCourses; course++) {
            if (!dfs(course, adjList, visiting)) {
                // Cycle detected → cannot finish all courses
                return false;
            }
        }

        // No cycles detected anywhere → safe to finish all courses
        return true;
    }

    // ------------------------------------------------------------
    // DFS FUNCTION — Returns false if a cycle is detected
    // ------------------------------------------------------------
    private boolean dfs(int course,
                        Map<Integer, List<Integer>> adjList,
                        Set<Integer> visiting) {

        // If the course is already in current recursion path,
        // we found a cycle.
        if (visiting.contains(course)) return false;

        // If this course has no outgoing edges,
        // it means it has already been processed and is safe.
        if (adjList.get(course).isEmpty()) return true;

        // Mark this course as being in current DFS path
        visiting.add(course);

        // Visit all dependent courses
        for (int nextCourse : adjList.get(course)) {
            if (!dfs(nextCourse, adjList, visiting)) {
                // If any deeper DFS finds a cycle → propagate failure
                return false;
            }
        }

        // DFS for this course completed safely.
        // Remove from current recursion stack
        visiting.remove(course);

        // Mark course as fully processed by clearing its adjacency list.
        // This avoids reprocessing in future DFS calls.
        adjList.put(course, new ArrayList<>());

        return true; // No cycle found from this path
    }
}
