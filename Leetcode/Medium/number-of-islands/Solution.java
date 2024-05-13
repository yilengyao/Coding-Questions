class Solution {
    static int[][] DIRECTIONS = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];

            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }

        int find(int node) {
            int nodeParent = parent[node];
            if (nodeParent != node) {
                parent[node] = find(nodeParent);
            }
            return parent[node];
        }

        void union(int nodeI, int nodeJ) {
            int rootI = find(nodeI);
            int rootJ = find(nodeJ);
            if (rootI == rootJ) return;
            if (size[rootI] > size[rootJ]) {
                parent[rootJ] = rootI;
                size[rootI] += size[rootJ];
            } else {
                parent[rootI] = rootJ;
                size[rootJ] += size[rootI];
            }
        }

        Set<Integer> distinctRoot() {
            Set<Integer> distinctRoot = new HashSet<>();
            for (int node = 0; node < parent.length; node++) {
                distinctRoot.add(find(node));
            }
            return distinctRoot;
        }
    }

    public int numIslands(char[][] grid) {
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        UnionFind uf = new UnionFind(rowLength * columnLength);
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                for (int[] direction: DIRECTIONS) {
                    int[] currentCell = new int[]{row, column};
                    int[] adjacentCell = new int[]{row + direction[0], column + direction[1]};
                    if (0 <= adjacentCell[0] && adjacentCell[0] < rowLength
                        && 0 <= adjacentCell[1] && adjacentCell[1] < columnLength
                        && grid[adjacentCell[0]][adjacentCell[1]] == '1' && grid[currentCell[0]][currentCell[1]] == '1') {
                            uf.union(flatten(currentCell[0], currentCell[1], columnLength), flatten(adjacentCell[0], adjacentCell[1], columnLength));
                        } 
                }
            }
        }
        // for (int i: uf.parent) {
        //     System.out.print(i + ",");
        // }
        Set<Integer> distinctRoots = uf.distinctRoot();
        int count = 0;
        for (int distinctRoot: distinctRoots) {
            int[] entry = unflatten(distinctRoot, columnLength);
            if (grid[entry[0]][entry[1]] == '1') {
                count++;
            }
        }
        return count;
    }

    public int flatten(int row, int column, int columnLength) {
        return (row * columnLength) + column;
    }

    public int[] unflatten(int node, int columnLength) {
        return new int[]{node / columnLength, node % columnLength};
    }
}