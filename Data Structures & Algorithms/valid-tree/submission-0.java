class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int v = edge[0];
            int u = edge[1];

            adjList.get(v).add(u);
            adjList.get(u).add(v);
        }

        Set<Integer> visited = new HashSet<>();
        checkValidTree(0, adjList, visited);

        return visited.size() == n;

    }

    private void checkValidTree(int node, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        if (visited.contains(node)) return;

        visited.add(node);

        for (int neighbour : adjList.get(node)) {
            checkValidTree(neighbour, adjList, visited);
        }
    }
}
