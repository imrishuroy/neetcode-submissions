/* 
INTUITION

We need to clone (deep copy) a graph.
Each node has:
- a value
- a list of neighbors

Graphs can have:
- cycles (A → B → C → A)
- multiple references to the same node

So, if we simply recursively copy nodes, we may:
- enter infinite loops due to cycles
- create multiple copies of the same node

To avoid this:
- We keep a HashMap that stores:
  Original Node  →  Cloned Node

Whenever we visit a node:
- If it is already cloned, reuse the clone
- Otherwise, create a new clone and store it in the map

We use DFS to traverse and clone the graph.
----------------------------------------------------
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {

    
    // Main function to start graph cloning
    public Node cloneGraph(Node node) {

        // If the input graph is empty, return null
        if (node == null) return null;

        // HashMap to store mapping:
        // Original Node -> Cloned Node
        // This prevents infinite loops in cyclic graphs
        Map<Node, Node> visited = new HashMap<>();

        // Start DFS cloning from the given node
        return dfs(node, visited);
    }

    // DFS helper function to clone graph recursively
    private Node dfs(Node node, Map<Node, Node> visited) {

        // If this node is already cloned, return its clone
        // This avoids duplicating nodes and handles cycles
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Step 1: Create a clone of the current node
        // Only value is copied now; neighbors will be added later
        Node copyNode = new Node(node.val);

        // Step 2: Store mapping in HashMap immediately
        // Important to do this BEFORE cloning neighbors
        // so that cyclic references are handled correctly
        visited.put(node, copyNode);

        // Step 3: Clone all neighbors recursively
        // For each neighbor of original node,
        // call dfs to get its cloned version,
        // then add it to the cloned node's neighbor list
        for (Node neighbor : node.neighbors) {
            copyNode.neighbors.add(dfs(neighbor, visited));
        }

        // Step 4: Return the cloned node
        return copyNode;
    }
}
