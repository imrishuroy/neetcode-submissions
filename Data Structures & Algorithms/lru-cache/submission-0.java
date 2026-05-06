class LRUCache {

    class DoublyNode {
        int key;
        int value;
        DoublyNode next;
        DoublyNode prev;

        public DoublyNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    Map<Integer, DoublyNode> map;
//     You have dummy head & dummy tail already set up like this:

// head <-> ... <-> tail


// head and tail NEVER move

// Real nodes are between them
    // Most Recently Used
    DoublyNode head;
    // Least Recently Used
    DoublyNode tail;
    int capacity;
    int size;
    


    public LRUCache(int capacity) {
        // dummy head and tail
        head = new DoublyNode(-1, -1);
        tail = new DoublyNode(-1, -1);

        head.next = tail;
        tail.prev = head;

        map = new HashMap<>(capacity);
        this.capacity = capacity;
        size = 0;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            DoublyNode node = map.get(key);
            removeNode(node);
            addToFront(node);
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        
        // if key already exists, update the value of the key and 
        // move the node to the front of the cache
        // if we are adding a new node, then we should check for capacity
        // if the capacity increse then the last node should be deleted
        if (map.containsKey(key)) {
            
            // move the node at the head (most recelty used)
            DoublyNode node = map.get(key);
            node.value = value;

            removeNode(node);
            addToFront(node);
        
        } else {
            // if key doesn't exists, then put the value in map and
            // add this node as the head of cache

            size++;

            DoublyNode newNode = new DoublyNode(key, value);
            map.put(key, newNode);
            addToFront(newNode);

            if (size > capacity) {
                DoublyNode prev = tail.prev;
                map.remove(prev.key);
                removeNode(prev);
                size--;
            }
        }
    }

    private void removeNode(DoublyNode node) {
        DoublyNode prev = node.prev;
        DoublyNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void addToFront(DoublyNode node) {
        DoublyNode first = head.next;

        node.next = first;
        node.prev = head;

        head.next = node;
        first.prev = node;
    }
}
