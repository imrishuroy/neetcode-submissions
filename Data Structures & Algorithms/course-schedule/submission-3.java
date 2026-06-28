class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build adjacency list (prereq -> course direction)
        // "I need to complete prereq BEFORE I can take course"
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Step 2: Track how many prerequisites each course has
        int[] indegree = new int[numCourses];

        // Step 3: Process each prerequisite pair
        for (int[] pre : prerequisites) {
            int prereq = pre[0]; // must be taken first
            int course = pre[1]; // depends on prereq

            // Edge: prereq -> course (prereq points to course)
            graph.get(prereq).add(course);

            // Course now has one more prerequisite to complete
            indegree[course]++;
        }

        // Step 4: Find courses with NO prerequisites (indegree == 0)
        // These are our starting points — we can take them immediately
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i); // This course is ready to take
            }
        }

        // Step 5: Process courses in topological order
        int completed = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll(); // Take this course
            completed++;

            // This course was a prerequisite for others
            // Now those courses have one fewer requirement
            for (int next : graph.get(current)) {
                indegree[next]--; // One prerequisite satisfied

                if (indegree[next] == 0) {
                    // All prerequisites done — ready to take!
                    queue.offer(next);
                }
            }
        }

        // Step 6: Did we complete all courses?
        // If not, there's a cycle (some courses depend on each other)
        return completed == numCourses;
    }
}
