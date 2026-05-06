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
    public void reorderList(ListNode head) {

        if (head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head;

        // becasuse we don't have to land at the middle element
        // we have to land at the end of first half list
        // also len of first half should be more thatn second half
        while (fast.next != null && fast.next.next != null) { 
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode first = head;
        ListNode second = reverseLL(slow.next);
        slow.next = null; // curr the list so avoid cycle

        // because the second list can have less items than first list
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            first = firstNext;
            second = secondNext;
        }

    }

    public ListNode reverseLL(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
