class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        
        int n = edges.length;

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            // Before adding edge (u, v) check if u can already reach v
            Set<Integer> visited = new HashSet<>();

            // if path exists -> adding this edge forms a cycle
            if (hasPath(u, v, -1, adjList, visited)) {
                return edge; // this is the redundant connection
            }

            // otherwise, safely add edge to graph
            adjList.get(u).add(v);
            adjList.get(v).add(u);
            
        }


        return new int[0];
    }

    private boolean hasPath(int src, int target, int parent, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        
        if (src == target) return true;

        visited.add(src);

        for (int nei: adjList.get(src)) {
            
            // Skip the edge leading back to the parent node.
            // In an undirected graph, every edge appears twice (u→v and v→u).
            // When DFS moves from parent → current node, the parent will appear
            // again in the neighbor list. Going back immediately is NOT a cycle,
            // it is just traversing the same edge in reverse, so we ignore it.
            if (nei == parent) continue;

            // visit unvisited neighbors
            if (!visited.contains(nei)) {
                if (hasPath(nei, target, src, adjList, visited)) return true;
            }

        }

        // no path found from this route
        return false;

    }

}
