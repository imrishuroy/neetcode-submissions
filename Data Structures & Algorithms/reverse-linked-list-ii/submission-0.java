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

    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next; // this node is the starting node of reversed LL

        ListNode prevNode = null;

        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = curr.next;
            curr.next = prevNode;
            prevNode = curr;
            curr = next;
        }

        // prevNode is now the head of reversed LL
        // curr is now at next node of right
        // prev is still poiting to the firstNode of reversed LL

        prev.next.next = curr;
        prev.next = prevNode;

        return dummy.next;
        
    }
}