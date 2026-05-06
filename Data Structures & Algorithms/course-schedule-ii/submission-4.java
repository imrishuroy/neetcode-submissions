class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        /*
         * Problem Intuition:
         * ------------------
         * Each course is a node in a directed graph.
         * A prerequisite pair [u, v] means:
         *    you must complete course v BEFORE course u.
         * So we create a directed edge: v -> u
         *
         * Our goals:
         * 1) Detect if a cycle exists.
         *    - If a cycle exists, it's impossible to finish all courses.
         * 2) If no cycle exists, return a valid ordering of courses.
         *
         * This is a classic:
         *   - Cycle detection in a directed graph
         *   - Topological Sorting problem
         *
         * We solve both using DFS.
         */

        // Adjacency list representation of graph: prerequisite -> list of dependent courses
        Map<Integer, List<Integer>> preMap = new HashMap<>();

        // State array for DFS cycle detection:
        // 0 = unvisited
        // 1 = visiting (currently in recursion stack)
        // 2 = visited (fully processed)
        int[] state = new int[numCourses];

        // Initialize adjacency list
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }

        // Build the graph
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];

            // prereq -> course edge
            preMap.get(prereq).add(course);
        }

        // This list will store the topological ordering (in reverse initially)
        List<Integer> result = new ArrayList<>();

        // Run DFS from every course to handle disconnected components
        for (int course = 0; course < numCourses; course++) {
            if (state[course] == 0) { 
                // If a cycle is detected, return empty array
                if (!dfs(course, preMap, state, result)) {
                    return new int[0];
                }
            }
        }

        /*
         * At this point:
         * result contains nodes in REVERSE topological order.
         * This happens because in DFS we add a course to result
         * only AFTER all its dependent courses are processed (postorder).
         *
         * So we reverse it to get the correct topological order.
         */
        Collections.reverse(result);

        // Convert List<Integer> to int[] as required by the problem
        return result.stream().mapToInt(i -> i).toArray();
    }


    private boolean dfs(int course,
                        Map<Integer, List<Integer>> preMap,
                        int[] state,
                        List<Integer> result) {

        /*
         * If we reach a node that is already in "visiting" state (state == 1),
         * it means we found a back-edge.
         * Back-edge in directed graph => cycle detected.
         * So return false.
         */
        if (state[course] == 1) return false;

        /*
         * If the node is already fully processed (state == 2),
         * no need to explore again — it is safe.
         */
        if (state[course] == 2) return true;

        // Mark current node as visiting (part of current DFS path)
        state[course] = 1;

        // Visit all dependent courses
        for (int nextCourse : preMap.get(course)) {
            if (!dfs(nextCourse, preMap, state, result)) {
                return false; // cycle detected in deeper recursion
            }
        }

        /*
         * All neighbors processed without finding a cycle.
         * Mark this node as fully visited.
         */
        state[course] = 2;

        /*
         * Add course to result AFTER processing its dependencies.
         * This is DFS postorder.
         * Produces reverse topological ordering.
         */
        result.add(course);

        return true; // no cycle found in
    }
}