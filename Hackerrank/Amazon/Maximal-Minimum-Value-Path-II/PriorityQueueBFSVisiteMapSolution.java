import java.util.*;

class Solution {
    public static int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    
    public int maximumMinimumPath(int[][] grid) {
        int numRows = grid.length;
        int numColumns = grid[0].length;

        boolean[][] visited = new boolean[numRows][numColumns];

        PriorityQueue<int[]> neighbours = new PriorityQueue<>((a, b) -> Integer.compare(grid[b[0]][b[1]], grid[a[0]][a[1]]));

        // initialize neighbours and visited
        neighbours.add(new int[]{0, 0});
        visited[0][0] = true;

        int maxMinValue = Math.min(grid[0][0], grid[numRows - 1][numColumns - 1]);

        while(!neighbours.isEmpty()) {
            int[] currentPath = neighbours.poll();
            visited[currentPath[0]][currentPath[1]] = true;
            maxMinValue = Math.min(maxMinValue, grid[currentPath[0]][currentPath[1]]);

            if (currentPath[0] == numRows - 1 && currentPath[1] == numColumns -1) {
                return maxMinValue;
            }

            for (int[] direction: DIRECTIONS) {
                int[] newPath = new int[]{currentPath[0] + direction[0], currentPath[1] + direction[1]};

                if (0 <= newPath[0] && newPath[0] < numRows
                    && 0 <= newPath[1] && newPath[1] < numColumns
                    && !visited[newPath[0]][newPath[1]]) {
                        neighbours.offer(newPath);
                }
            }
        }

        return -1;
    }
}