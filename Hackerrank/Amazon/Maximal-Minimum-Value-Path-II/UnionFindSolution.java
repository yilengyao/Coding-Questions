class Solution {
    public static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int length) {
            this.parent = IntStream.range(0, length).toArray();
            this.size = IntStream.range(0, length).map(i -> 1).toArray();
        }

        public int find(int node) {
            int parent = this.parent[node];
            if (parent == node) {
                return node;
            }
            this.parent[node] = this.find(parent);
            return this.parent[node];
        } 

        public void union(int nodeI, int nodeJ) {
            int rootI = this.find(nodeI);
            int rootJ = this.find(nodeJ);

            if (rootI == rootJ) {
                return;
            }
            if (this.size[rootI] > this.size[rootJ]) {
                this.parent[rootJ] = rootI;
                this.size[rootI] += this.size[rootJ];
            } else {
                this.parent[rootI] = rootJ;
                this.size[rootJ] += this.size[rootI];
            }
            return;
        }
    }
    
    public int maximumMinimumPath(int[][] grid) {
        int numRows = grid.length;
        int numColumns = grid[0].length;
        UnionFind uf = new UnionFind( numRows * numColumns );
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(grid[b[0]][b[1]], grid[a[0]][a[1]]));
        
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                maxHeap.offer(new int[]{row, column});
            }
        }
        
        while (!maxHeap.isEmpty()) {
            int[] currentPosition = maxHeap.poll();
            int maxMinValue = grid[currentPosition[0]][currentPosition[1]];
            for (int[] direction: DIRECTIONS) {
                int[] adjacentPosition = {currentPosition[0] + direction[0], currentPosition[1] + direction[1]};
                if (0 <= adjacentPosition[0] && adjacentPosition[0] < numRows
                && 0 <= adjacentPosition[1] && adjacentPosition[1] < numColumns
                && grid[adjacentPosition[0]][adjacentPosition[1]] >= maxMinValue) {

                    uf.union(currentPosition[0] * numColumns + currentPosition[1], adjacentPosition[0] * numColumns + adjacentPosition[1]);
                }
            }

            if (uf.find(0) == uf.find(numRows * numColumns - 1)) {
                return maxMinValue;
            }
        }

        return -1;
    }
}