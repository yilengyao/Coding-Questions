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
        if (k == 1) return head;
        ListNode result = new ListNode(0, head);
        ListNode prev = result;
        head = result;
        ListNode tail = result;
        ListNode next = result;
        ListNode lav = result;

        while(true) {
            head = prev.next;

            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return result.next;
                }
            }

            if (tail == null ) {
                return result.next;
            }

            next = tail.next;

            reverse(head, k);

            prev.next = tail;
            head.next = next;
            prev = head;
  
            tail = head;
        }
    }

    public void reverse(ListNode head, int n) {
        ListNode prev = head;
        ListNode current = head.next;
        if (n == 2) {
            current.next = prev;
        }
        ListNode next = head.next.next;
        for (int i = 0; i < n - 2; i++) {
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        }
        current.next = prev;
    }
}