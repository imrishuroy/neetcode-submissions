class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // 1. Find middle
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Split list
        prev.next = null;

        // 3. Reverse second half
        ListNode list1 = head;
        ListNode list2 = reverseLL(slow);

        // 4. Merge safely
        ListNode curr = list1;

        while (list1 != null && list2 != null) {
            ListNode t1 = list1.next;
            ListNode t2 = list2.next;

            list1.next = list2;
            list2.next = t1;

            curr = list2;   // keep track of last node
            list1 = t1;
            list2 = t2;
        }

        // 5. Attach leftover middle node (odd length)
        if (list2 != null) {
            curr.next = list2;
        }
    }

    private ListNode reverseLL(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
