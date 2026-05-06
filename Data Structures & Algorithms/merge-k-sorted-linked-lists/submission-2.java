/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        
        // min heap based on node value
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Push only the head of each non-empty list
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        // Dummy node to simplify result construction
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        // extract min and push node from the same list
        while (!pq.isEmpty()) {
            // get the samllest node
            ListNode smallest = pq.poll();

            // add it the merged list
            curr.next = smallest;
            curr = curr.next;

            // push the next node from this list (if exists)
            if (smallest.next != null) {
                pq.offer(smallest.next);
            }
        }

        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (ListNode node : lists) {

            while (node != null) {
                pq.add(node.val);
                node = node.next;
            }
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            curr.next = new ListNode(pq.poll());
            curr = curr.next;
        }

        return dummy.next;

    }
}
