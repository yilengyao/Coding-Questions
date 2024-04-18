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

    static class NodeEntry implements Comparable<NodeEntry> {
        double average;
        TreeNode node;

        public NodeEntry(double average, TreeNode node) {
            this.average = average;
            this.node = node;
        }

        @Override
        public int compareTo(NodeEntry otherNode) {
            return Double.compare(otherNode.average, this.average);
        }
    }

    public double maximumAverageSubtree(TreeNode root) {
        PriorityQueue<NodeEntry> maxHeap = new PriorityQueue<>();

        maximumAverageSubtreeHelper(root, maxHeap);
        return maxHeap.poll().average;
    }

    public int[] maximumAverageSubtreeHelper(TreeNode node, PriorityQueue<NodeEntry> maxHeap) {
        int[] state = new int[2];
        if (node.left != null) {
            int[] l = maximumAverageSubtreeHelper(node.left, maxHeap);
            state[0] += l[0];
            state[1] += l[1];
        }
        if (node.right != null) {
            int[] r = maximumAverageSubtreeHelper(node.right, maxHeap);
            state[0] += r[0];
            state[1] += r[1];
        }

        state[0] += node.val;
        state[1]++;
        maxHeap.add(new NodeEntry((double) state[0] / state[1], node));

        return state;
    }
}