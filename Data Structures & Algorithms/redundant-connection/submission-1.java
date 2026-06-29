class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n + 1];
        int[] size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (find(u, parent) == find(v, parent)) {
                return edge;
            }

            union(u, v, parent, size);
        }

        return new int[0];
    }

    int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    void union(int x, int y, int[] parent, int[] size) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if (rootX == rootY) return;

        // union by size: attach smaller tree under larger tree
        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
}
