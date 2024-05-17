class Solution {
    static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int islandPerimeter(int[][] grid) {
        int numRows = grid.length;
        int numColumns = grid[0].length;
        int perimeter = 0;
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                for (int[] direction: DIRECTIONS) {
                    int[] neighbour = new int[]{row + direction[0], column + direction[1]};
                    if ((neighbour[0] < 0 || neighbour[0] >= numRows || neighbour[1] < 0 || neighbour[1] >= numColumns || grid[neighbour[0]][neighbour[1]] == 0) && grid[row][column] == 1 ) {
                        perimeter++;
                    }

                }
            }
        }
        return perimeter;
    }
}