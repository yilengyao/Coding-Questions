/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stackP = new ArrayDeque<>();
        Deque<TreeNode> stackQ = new ArrayDeque<>();
        findVal(root, p.val, stackP);
        findVal(root, q.val, stackQ);

        Deque<TreeNode> pathP = new ArrayDeque<>();
        Deque<TreeNode> pathQ = new ArrayDeque<>();
        while (!stackP.isEmpty()) {
            pathP.push(stackP.poll());
        }
        while (!stackQ.isEmpty()) {
            pathQ.push(stackQ.poll());
        }

        TreeNode lca = root;
        while(pathP.peek() == pathQ.peek()) {
            lca = pathP.poll();
            pathQ.poll();
        }
        return lca;
    }

    boolean findVal(TreeNode node, int val, Deque<TreeNode> path) {
        if (node==null) {
            return false;
        }
        path.push(node);
        if (node.val == val) {
            return true;
        }

        if (findVal(node.left, val, path)) {
            return true;
        }

        if (findVal(node.right, val, path)) {
            return true;
        }

        path.poll();
        return false;
    }
}