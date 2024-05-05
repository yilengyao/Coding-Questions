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
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode node: lists) {
            if (node != null) {
                minHeap.offer(node);
                while (node.next != null) {
                    node = node.next;
                    minHeap.offer(node);
                }
            }
        }

        ListNode result = minHeap.poll();
        ListNode walker = result;
        while (!minHeap.isEmpty()) {
            ListNode nextNode = minHeap.poll();
            walker.next = nextNode;
            walker = nextNode;
        }
        if (result != null) {
            walker.next = null;
        }
        return result;
    }
}