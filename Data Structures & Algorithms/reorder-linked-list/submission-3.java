/**
 * Problem:
 * Reorder a linked list like this:
 *
 * 1 -> 2 -> 3 -> 4 -> 5
 *
 * becomes
 *
 * 1 -> 5 -> 2 -> 4 -> 3
 *
 * ---------------------------------------------------
 * Intuition:
 *
 * We need nodes from:
 * - start of list
 * - end of list
 * - then next from start
 * - then next from end
 *
 * Since linked list cannot move backwards,
 * we do this in 3 steps:
 *
 * 1. Find middle of linked list
 * 2. Reverse second half
 * 3. Merge both lists alternately
 *
 * ---------------------------------------------------
 * Example:
 *
 * Original:
 * 1 -> 2 -> 3 -> 4 -> 5
 *
 * Step 1: Find middle
 * First half:  1 -> 2 -> 3
 * Second half: 4 -> 5
 *
 * Step 2: Reverse second half
 * 5 -> 4
 *
 * Step 3: Merge
 * 1 -> 5 -> 2 -> 4 -> 3
 */

class Solution {

    public void reorderList(ListNode head) {

        // Edge case:
        // If list has 0 or 1 node,
        // no reordering needed
        if (head == null || head.next == null) {
            return;
        }

        // -----------------------------------
        // STEP 1: Find middle of linked list
        // -----------------------------------

        /*
         * slow moves 1 step
         * fast moves 2 steps
         *
         * When fast reaches end,
         * slow will be at middle
         */

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // -----------------------------------
        // STEP 2: Reverse second half
        // -----------------------------------

        /*
         * Example:
         *
         * Before reverse:
         * 4 -> 5
         *
         * After reverse:
         * 5 -> 4
         */

        ListNode second = reverse(slow.next);

        // Break the list into two halves
        slow.next = null;

        // First half starts from head
        ListNode first = head;

        // -----------------------------------
        // STEP 3: Merge both halves
        // -----------------------------------

        /*
         * first  = 1 -> 2 -> 3
         * second = 5 -> 4
         *
         * Merge like:
         *
         * 1 -> 5 -> 2 -> 4 -> 3
         */

        while (second != null) {

            // Store next nodes
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            // Connect first node to second node
            first.next = second;

            // Connect second node to next node of first half
            second.next = temp1;

            // Move pointers forward
            first = temp1;
            second = temp2;
        }
    }

    // Helper function to reverse linked list
    private ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            // Store next node
            ListNode next = curr.next;

            // Reverse link
            curr.next = prev;

            // Move pointers forward
            prev = curr;
            curr = next;
        }

        // prev becomes new head
        return prev;
    }
}