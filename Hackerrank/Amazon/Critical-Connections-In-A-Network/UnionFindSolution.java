class Solution {
    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);  // Path compression
            }
            return parent[node];
        }

        public void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if (root1 != root2) {
                if (rank[root1] > rank[root2]) {
                    parent[root2] = root1;
                } else if (rank[root1] < rank[root2]) {
                    parent[root1] = root2;
                } else {
                    parent[root2] = root1;
                    rank[root1] += 1;
                }
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Set<Integer> nodes = new HashSet<>();
        Set<List<Integer>> candidateSet = new HashSet<>();
        Set<List<Integer>> slackEdges = new HashSet<>();
        for (List<Integer> connection: connections) {
            nodes.add(connection.get(0));
            nodes.add(connection.get(1));
            candidateSet.add(connection);
        }

        int candidateSize = 0;
        
        
        while (candidateSize != candidateSet.size()) {
            UnionFind uf = new UnionFind(n);
            candidateSize = candidateSet.size();

            for (List<Integer> slackEdge: slackEdges) {
                Integer nodeI = slackEdge.get(0);
                Integer nodeJ = slackEdge.get(1);
                uf.union(nodeI, nodeJ);
            }

            Iterator<List<Integer>> iterator = candidateSet.iterator();
            while (iterator.hasNext()) {
                List<Integer> candidateEdge = iterator.next();
                Integer nodeI = candidateEdge.get(0);
                Integer nodeJ = candidateEdge.get(1);

                if (uf.find(nodeI) == uf.find(nodeJ)) {
                    slackEdges.add(candidateEdge);
                    iterator.remove(); 
                } else {
                    uf.union(nodeI, nodeJ);
                }
            }
        }

  
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> edge: candidateSet) {
            result.add(edge);
        }
        return result;
    }
}