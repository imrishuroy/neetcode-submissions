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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || head.next == null) return null;

        ListNode newHead = reverseLL(head);

        ListNode temp = newHead;
        while (temp != null) {
            System.out.print(temp.val);
            temp = temp.next;
        }

        if (n == 1) return reverseLL(newHead.next);

        int index = 1;
        ListNode prev = null;
        ListNode curr = newHead;


        while (curr != null) {
            if (index == n) {
                prev.next = curr.next;
                curr = null;
                break;
            }
            index++;
            prev = curr;
            curr = curr.next;
        }

        return reverseLL(newHead);
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
