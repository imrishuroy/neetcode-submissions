class Solution {
    // Detect if a directed graph has a cycle.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        // create the graph
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int u = prerequisite[0];
            int v = prerequisite[1];

            adjList.get(v).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        
        for (int course = 0; course < numCourses; course++) {
           if (!dfs(course, adjList, visited)) return false;
        }

        return true;

    }

    private boolean dfs(int course, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        if (visited.contains(course)) return false;
        if (adjList.get(course).isEmpty()) return true;

        visited.add(course);

        for (int node : adjList.get(course)) {
            if (!dfs(node, adjList, visited)) return false;
            
        }

        visited.remove(course);
        adjList.put(course, new ArrayList<>());

        return true;
    } 
}
