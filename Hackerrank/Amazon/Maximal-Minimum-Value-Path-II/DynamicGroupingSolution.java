class Solution {
    public static int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static class Position {
        int row;
        int column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && column == position.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return "Position{" +
                   "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }
    
    public int maximumMinimumPath(int[][] grid) {
        int numRows = grid.length;
        int numColumns = grid[0].length;

        PriorityQueue<Position> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(grid[b.row][b.column], grid[a.row][a.column]));

        List<Set<Position>> neighbours = new LinkedList<>();

        Position beginning = new Position(0, 0);
        Position end = new Position(numRows - 1, numColumns - 1);

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                maxHeap.offer(new Position(row, column));
            }
        }

        while (!maxHeap.isEmpty()) {
            Set<Position> currentNeighbour = new HashSet();
            Position currentPosition = maxHeap.poll();
            currentNeighbour.add(currentPosition);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                Set<Position> neighbour = neighbours.get(i);
                Set<Position> directions = new HashSet<>();
                for(int[] direction: DIRECTIONS) {
                    directions.add(new Position(currentPosition.row + direction[0], currentPosition.column + direction[1]));
                }
                directions.retainAll(neighbour);
                if (!directions.isEmpty()) {
                    currentNeighbour.addAll(neighbour);
                    neighbours.remove(i);
                }
            }
            if (currentNeighbour.contains(beginning) && currentNeighbour.contains(end)) {
                return grid[currentPosition.row][currentPosition.column];
            }


            neighbours.add(currentNeighbour);

        }



        return -1;
    }
}