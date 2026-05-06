/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {  

        Node curr = head;
        Map<Node, Node> map = new HashMap<>();

        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        Node dummy = new Node(-1);
        Node copyNode = dummy;

        while (curr != null) {
            copyNode.next = map.get(curr);
            copyNode.next.random = map.get(curr.random);
            copyNode = copyNode.next;
            curr = curr.next;
        }

        return dummy.next;
        
    }
}
