import java.util.*;

public class Solution {
    static class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> size;
        
        public UnionFind(Set<Integer> nodes) {
            this.parent = new HashMap<>();
            this.size = new HashMap<>();
            for (Integer node: nodes) {
                this.parent.computeIfAbsent(node, i -> node);
                this.size.computeIfAbsent(node, i -> 1);
            }
        }
        
        public int find(int node) {
            int parent = this.parent.get(node);
            if (parent == node) return node;
            this.parent.put(node, this.find(parent));
            return this.parent.get(node);
        }
        
        public void union(int nodeI, int nodeJ) {
            int rootI = this.find(nodeI);
            int rootJ = this.find(nodeJ);
            if (rootI == rootJ) return;
            
            int sizeI = this.size.get(rootI);
            int sizeJ = this.size.get(rootJ);
            
            if (sizeI > sizeJ) {
                this.parent.put(rootJ, rootI);
                this.size.put(rootI, sizeI + sizeJ);
            } else {
                this.parent.put(rootI, rootJ);
                this.size.put(rootJ, sizeI + sizeJ);
            }
        }
    }
    
    public int minCostToRepairEdges(int n, int[][] edges, int[][] edgesToRepair) {
        if(edges == null || edgesToRepair == null) return -1;
        if(edges.length == 0 && edgesToRepair.length == 0) return -1;
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((edge1, edge2) -> Integer.compare(edge1[2], edge2[2]));
        Set<Integer> nodes = new HashSet<>();
        Set<List<Integer>> seenEdges = new HashSet<>();
        
        for (int[] edge: edgesToRepair) {
            minHeap.offer(edge);
            nodes.add(edge[0]);
            nodes.add(edge[1]);
            seenEdges.add(Arrays.asList(edge[0], edge[1]));
        }
        
        for (int[] edge: edges) {
            List<Integer> edgeList1 = Arrays.asList(edge[0], edge[1]);
            List<Integer> edgeList2 = Arrays.asList(edge[1], edge[0]);

            if (!seenEdges.contains(edgeList1) && !seenEdges.contains(edgeList2)) {
                nodes.add(edge[0]);
                nodes.add(edge[1]);
                minHeap.offer(new int[]{edge[0], edge[1], 0});
            }
        }
        
        UnionFind uf = new UnionFind(nodes);
        int minCost = 0;
        int numEdges = 0;

        while (!minHeap.isEmpty()) {

            int[] edgeToAdd = minHeap.poll();

            int node1 = edgeToAdd[0];
            int node2 = edgeToAdd[1];
            int cost = edgeToAdd[2];
            
            int root1 = uf.find(node1);
            int root2 = uf.find(node2);

            if (root1 != root2) {
                minCost += cost;
                numEdges++;
                uf.union(root1, root2);
            }
            
            if (numEdges == n - 1) return minCost;
        }
        
        if (numEdges == n - 1) return minCost;
        
        return -1;
    }
}