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

        // Edge case:
        // If list has 0 or 1 node, it is already "reordered"
        if (head == null || head.next == null) return;

        // Step 1: Find the end of the FIRST half of the list
        // ---------------------------------------------------
        // We use slow & fast pointers.
        // IMPORTANT CHOICE:
        // We do NOT want slow to land on the exact middle.
        // We want slow to stop at the END of the first half.
        //
        // Why?
        // - We will reverse the second half starting from slow.next
        // - The first half should be >= second half in length
        // - This guarantees safe merging without losing nodes
        //
        // Condition explanation:
        // fast.next != null       -> fast can move one step
        // fast.next.next != null  -> fast can move two steps
        //
        // This ensures slow stops earlier (end of first half),
        // not at the exact middle (which can break merging).
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;          // move slow by 1
            fast = fast.next.next;     // move fast by 2
        }

        // At this point:
        // slow -> last node of first half
        // slow.next -> head of second half

        // Step 2: Reverse the second half
        // --------------------------------
        // We reverse from slow.next instead of slow
        // so that the middle element (if any) stays in first half
        ListNode first = head;
        ListNode second = reverseLL(slow.next);

        // Cut the list to avoid cycles after reversing
        // Now:
        // first half ends at slow
        // second half is a separate reversed list
        slow.next = null;

        // Step 3: Merge the two halves alternately
        // -----------------------------------------
        // Pattern required:
        // L0 → Ln → L1 → Ln-1 → L2 → Ln-2 ...
        //
        // IMPORTANT CHOICE:
        // We loop while (second != null)
        // because the second half can be shorter than the first.
        // The first half may have one extra node (odd length case),
        // which should remain at the end naturally.
        while (second != null) {

            // Save next pointers before rewiring
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            // Place one node from second half after one from first half
            first.next = second;
            second.next = firstNext;

            // Move forward in both lists
            first = firstNext;
            second = secondNext;
        }
    }

    // Helper method to reverse a linked list
    // ---------------------------------------
    // Standard iterative reversal using three pointers:
    // prev <- curr -> next
    public ListNode reverseLL(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next; // store next node
            curr.next = prev;          // reverse link
            prev = curr;               // move prev forward
            curr = next;               // move curr forward
        }

        // prev becomes the new head after reversal
        return prev;
    }
}
