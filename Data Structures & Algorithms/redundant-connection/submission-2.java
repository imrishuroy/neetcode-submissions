class Solution {

    /*
     * Intuition:
     * ----------
     * A tree with N nodes always has exactly (N - 1) edges and contains no cycles.
     * Here, one extra edge has been added, which creates exactly one cycle.
     *
     * We process edges one by one.
     *
     * Before connecting two nodes, we check:
     * - Are they already connected?
     * - If yes, adding this edge would create a cycle.
     *   Therefore, this edge is the redundant connection.
     *
     * To efficiently determine whether two nodes already belong to the same
     * connected component, we use Disjoint Set Union (Union Find).
     */

    /*
     * Approach:
     * ----------
     * 1. Initially every node is its own parent.
     *    (Each node forms its own separate component.)
     *
     * 2. Process every edge.
     *
     *      u ----- v
     *
     *    - Find the parent (root) of both nodes.
     *    - If both have the same root,
     *      they are already connected,
     *      so this edge creates a cycle.
     *
     *    - Otherwise, merge both components using Union.
     *
     * 3. Return the first edge that forms a cycle.
     *
     * Optimizations:
     * - Path Compression:
     *     Makes future Find operations almost O(1).
     *
     * - Union by Size:
     *     Always attach the smaller tree under the larger tree
     *     to keep the tree shallow.
     */

    public int[] findRedundantConnection(int[][] edges) {

        // Number of nodes.
        // Since there are N edges in the input,
        // nodes are numbered from 1 to N.
        int n = edges.length;

        // parent[i] = parent of node i
        int[] parent = new int[n + 1];

        // size[i] = size of the tree whose root is i
        int[] size = new int[n + 1];

        // Initially every node is its own parent.
        // Every component has size 1.
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // Process every edge one by one.
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            // Find the representative (root) of both nodes.
            // If both roots are same,
            // they already belong to the same connected component.
            // Adding this edge will create a cycle.
            if (find(u, parent) == find(v, parent)) {
                return edge;
            }

            // Otherwise merge both components.
            union(u, v, parent, size);
        }

        // Problem guarantees one redundant edge,
        // so this line will never practically execute.
        return new int[0];
    }

    /*
     * Find Operation
     * --------------
     * Returns the representative (root) of the component.
     *
     * Path Compression:
     * While returning from recursion,
     * directly connect every node to the root.
     *
     * Before:
     * 1 -> 2 -> 3 -> 4
     *
     * After find(1):
     * 1 ----\
     * 2 ----- > 4
     * 3 ----/
     *
     * This makes future Find operations much faster.
     */
    int find(int x, int[] parent) {

        // If x is not the root,
        // recursively find the root
        // and compress the path.
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }

    /*
     * Union Operation
     * ---------------
     * Merge two connected components.
     *
     * We first find the root of both nodes.
     *
     * If both roots are already same,
     * nothing to merge.
     *
     * Otherwise,
     * attach the smaller tree under the larger tree.
     *
     * This keeps the tree balanced,
     * reducing future Find time.
     */
    void union(int x, int y, int[] parent, int[] size) {

        int rootX = find(x, parent);
        int rootY = find(y, parent);

        // Already in the same component.
        if (rootX == rootY)
            return;

        // Attach smaller tree under larger tree.
        if (size[rootX] < size[rootY]) {

            parent[rootX] = rootY;
            size[rootY] += size[rootX];

        } else {

            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
}

/*
 * Time Complexity:
 * ----------------
 * Initialization:
 * O(N)
 *
 * For every edge:
 * - Find() is nearly O(1) because of
 *   Path Compression + Union by Size.
 *
 * Total:
 * O(N * α(N))
 *
 * where α(N) is the Inverse Ackermann Function,
 * which grows so slowly that it is practically constant.
 *
 * Therefore,
 * Overall Time Complexity ≈ O(N)
 *
 *
 * Space Complexity:
 * -----------------
 * parent[] = O(N)
 * size[]   = O(N)
 *
 * Total:
 * O(N)
 */