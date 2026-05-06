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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // groupPrev always points to the node just before the current group of k nodes
        ListNode groupPrev = dummy;

        while (true) {
            // check if k nodes exits ahead

            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) break;

            // groupstart -> first node of the group
            // groupNext -> node after the kth node
            ListNode groupStart = groupPrev.next;
            ListNode groupNext = kth.next;

            // reverse the current k group
            ListNode newGroupHead = reverseLL(groupStart, groupNext);
            groupPrev.next = newGroupHead;

            groupPrev = groupStart;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    private ListNode reverseLL(ListNode start, ListNode end) {
        ListNode curr = start;
        ListNode prev = end;

        while (curr != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }
}
