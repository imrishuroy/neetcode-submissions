class Solution {
    public int countComponents(int n, int[][] edges) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                count++;
                dfs(i, adjList, visited);
            }
        }

        return count;

    }

    private void dfs(int node, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        if (visited.contains(node)) return;

        visited.add(node);

        for (int nei : adjList.get(node)) {
            dfs(nei, adjList, visited);
        }
    }
}
