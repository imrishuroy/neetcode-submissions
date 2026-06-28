class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int[] preq : prerequisites) {
            int course = preq[0]; // course
            int prereq = preq[1]; // prerequisites

            graph.get(prereq).add(course);
            indegree[course]++;

        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completed = 0;
        int[] result = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();

            completed++;
            result[index++] = course;

            for (int next : graph.get(course)) {
                indegree[next]--; // becase we have already done the prereq of this course
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return completed == numCourses ? result : new int[0];

    }
}
