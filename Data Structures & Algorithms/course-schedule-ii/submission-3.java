class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // we have to check if we can complete all the courses
        // if we can, then we have return valid odering of courses
        // detect if there is a cycle, then we can complete all the courses, hence return the empty array

        Map<Integer, List<Integer>> preMap = new HashMap<>();
        int[] state = new int[numCourses]; // O -> unvisited, 1 -> visiting, 2 -> visited

        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }

        for (int[] prerequisite: prerequisites) {
            int u = prerequisite[0];
            int v = prerequisite[1];

            preMap.get(v).add(u); // v -> u
        }

        List<Integer> result = new ArrayList<>();

        for (int course = 0; course < numCourses; course++) {
            if (state[course] == 0) {
                if (!dfs(course, preMap, state, result)) return new int[0];
            }
            
        }

        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();

    }

    private boolean dfs(int course, Map<Integer, List<Integer>> preMap, int[] state, List<Integer> result) {
        // node came again in same recursion call stack
        if (state[course] == 1) return false; 

        // already processed this code
        if (state[course] == 2) return true; 

        state[course] = 1;

        for (int nei: preMap.get(course)) {
            if (!dfs(nei, preMap, state, result)) return false;
        }

        state[course] = 2;
        result.add(course);
        return true;
    }
}
