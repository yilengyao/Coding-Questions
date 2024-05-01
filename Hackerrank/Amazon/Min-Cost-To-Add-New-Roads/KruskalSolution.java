class Solution {

    static class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> size;

        public UnionFind(int[][] connections) {
            this.parent = new HashMap<>();
            this.size = new HashMap<>();
            Stream.of(connections).forEach( connection -> {
                int i = connection[0];
                int j = connection[1];
                this.parent.put(i, i);
                this.parent.put(j, j);
                this.size.put(i, 1);
                this.size.put(j, 1);
            });
        }

        public int find(int node) {
            int parent = this.parent.get(node);
            if (node == parent) return node;
            this.parent.put(node, this.find(parent));
            return this.parent.get(node);
        }

        public void union(int nodeI, int nodeJ) {
            int rootI = this.find(nodeI);
            int rootJ = this.find(nodeJ);
            if (rootI == rootJ) return;
            if (this.size.get(rootI) > this.size.get(rootJ)) {
                this.parent.put(rootJ, rootI);
                this.size.put(rootI, this.size.get(rootI) + this.size.get(rootJ));
            } else {
                this.parent.put(rootI, rootJ);
                this.size.put(rootJ, this.size.get(rootJ) + this.size.get(rootI));
            }
        }
    }

    public int minimumCost(int n, int[][] connections) {
        if (n == 1) return 0;
        UnionFind uf = new UnionFind(connections);
        Arrays.sort(connections, (a, b) -> Integer.compare(a[2], b[2]));
        int numRows = connections.length;
        int minCost = 0;
        int numEdges = 0;

        for (int i = 0; i < numRows; i++) {
            int nodeI = connections[i][0];
            int nodeJ = connections[i][1];
            int weight = connections[i][2];

            if (uf.find(nodeI) != uf.find(nodeJ)) {
                minCost += weight;
                uf.union(nodeI, nodeJ);
                numEdges++;
                if (numEdges == n - 1) {
                    return minCost;
                }
            }
        }
        if (numEdges - 1 == n) {
            return minCost;
        }
        return -1;
    }
}