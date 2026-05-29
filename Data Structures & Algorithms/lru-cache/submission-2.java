/*
APPROACH

We need an LRU (Least Recently Used) Cache where:

1. get(key)
   - Return the value if key exists.
   - Mark the key as recently used.

2. put(key, value)
   - Insert/update the key.
   - Mark it as recently used.
   - If cache exceeds capacity, remove the least recently used key.

DATA STRUCTURES

1. HashMap<Key, Node>
   - Gives O(1) access to any key.

2. Doubly Linked List
   - Stores nodes in usage order.
   - Most Recently Used (MRU) node is near HEAD.
   - Least Recently Used (LRU) node is near TAIL.

   head <-> MRU ... LRU <-> tail

WHY DO WE NEED BOTH?

HashMap:
- Fast lookup by key in O(1).

Doubly Linked List:
- Fast removal and insertion in O(1).

Together they allow both get() and put() to work in O(1).
*/

class LRUCache {

    /*
     * Each cache entry is stored as a node in the doubly linked list.
     */
    class DoublyNode {
        int key;
        int val;

        DoublyNode prev;
        DoublyNode next;

        public DoublyNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // Maximum number of items allowed in cache.
    int capacity;

    // Key -> Node mapping for O(1) lookup.
    Map<Integer, DoublyNode> map;

    // Dummy head and tail nodes.
    // They help avoid null checks while inserting/removing nodes.
    DoublyNode head;
    DoublyNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        map = new HashMap<>(capacity);

        // Create dummy nodes.
        head = new DoublyNode(-1, -1);
        tail = new DoublyNode(-1, -1);

        // Initially:
        // head <-> tail
        head.next = tail;
        tail.prev = head;
    }

    /*
     * GET OPERATION
     *
     * If key exists:
     * 1. Move node to front because it was just used.
     * 2. Return its value.
     *
     * If key doesn't exist:
     * Return -1.
     */
    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }

        DoublyNode node = map.get(key);

        // Since this key was accessed,
        // it becomes the most recently used.
        removeNode(node);
        addToFront(node);

        return node.val;
    }

    /*
     * PUT OPERATION
     *
     * CASE 1:
     * Key already exists.
     * - Update value.
     * - Move node to front.
     *
     * CASE 2:
     * Key does not exist.
     * - If cache is full, remove LRU node.
     * - Insert new node at front.
     */
    public void put(int key, int value) {

        // Existing key.
        if (map.containsKey(key)) {

            DoublyNode node = map.get(key);

            // Update value.
            node.val = value;

            // Move to front because it was used.
            removeNode(node);
            addToFront(node);

            return;
        }

        // Cache is full.
        if (map.size() >= capacity) {

            // Node before tail is always LRU.
            DoublyNode lruNode = tail.prev;

            // Remove from linked list.
            removeNode(lruNode);

            // Remove from hashmap.
            map.remove(lruNode.key);
        }

        // Create new cache entry.
        DoublyNode newNode = new DoublyNode(key, value);

        // New node becomes most recently used.
        addToFront(newNode);

        // Store in hashmap.
        map.put(key, newNode);
    }

    /*
     * Add node immediately after head.
     *
     * Before:
     * head <-> A <-> B
     *
     * Insert X:
     * head <-> X <-> A <-> B
     *
     * This makes X the most recently used node.
     */
    private void addToFront(DoublyNode node) {

        // Current first real node.
        DoublyNode next = head.next;

        // Connect new node.
        node.next = next;
        node.prev = head;

        // Fix surrounding pointers.
        next.prev = node;
        head.next = node;
    }

    /*
     * Remove a node from the linked list.
     *
     * Before:
     * A <-> X <-> B
     *
     * After:
     * A <-> B
     *
     * We simply connect X's previous
     * and next nodes together.
     */
    private void removeNode(DoublyNode node) {

        DoublyNode prev = node.prev;
        DoublyNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }
}

/*
TIME COMPLEXITY

get()  -> O(1)
put()  -> O(1)

SPACE COMPLEXITY

O(capacity)

for hashmap + doubly linked list nodes.
*/