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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pseudoHead = new ListNode(Integer.MAX_VALUE);
        pseudoHead.next = head;
        head = pseudoHead;
        ListNode current = head.next;

        while (current != null && head != null) {

            while (current.next!= null && current.val == current.next.val) {
                current = current.next;
            }
            
            if (head.next != current) {

                head.next = current.next;
                current = current.next;
            } else {
                head = head.next;
                current = head.next;
            }

        }
        return pseudoHead.next;
    }
}