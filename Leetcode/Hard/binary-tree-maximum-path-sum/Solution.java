/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxPathSum(TreeNode root) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        DFS(root, maxHeap);
        return maxHeap.poll();
    }

    public int DFS(TreeNode node, PriorityQueue maxHeap) {
        if (node == null) {
            return 0;
        }

        int leftSum = Math.max(DFS(node.left, maxHeap), 0);
        int rightSum = Math.max(DFS(node.right, maxHeap), 0);

        maxHeap.offer(node.val + leftSum + rightSum);

        int sum = node.val + Math.max(leftSum, rightSum);

        maxHeap.offer(sum);

        return sum;
    }
}