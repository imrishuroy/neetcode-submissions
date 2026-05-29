class LRUCache {

    class DoublyNode {
        int key;
        int val;
        DoublyNode prev;
        DoublyNode next;


        public DoublyNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }


    }

    int capacity;
    Map<Integer, DoublyNode> map;
    DoublyNode head;
    DoublyNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new DoublyNode(-1, -1);
        tail = new DoublyNode(-1, -1);

        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            DoublyNode node = map.get(key);
            removeNode(node);
            addToFront(node);
            return node.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {

        if (map.containsKey(key)) {
            DoublyNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToFront(node);
        } else {
            if (map.size() >= capacity) { 
                DoublyNode node = tail.prev;
                removeNode(node);
                map.remove(node.key);

                DoublyNode newNode = new DoublyNode(key, value);
                addToFront(newNode);
                map.put(key, newNode);
            } else {
                DoublyNode newNode = new DoublyNode(key, value);
                addToFront(newNode);
                map.put(key, newNode);
            }
        }
    }

    private void addToFront(DoublyNode node) {
        DoublyNode next = head.next;

        node.next = next;
        node.prev = head;

        next.prev = node;
        head.next = node;
    }

    private void removeNode(DoublyNode node) {
        DoublyNode prev = node.prev;
        DoublyNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }
}
