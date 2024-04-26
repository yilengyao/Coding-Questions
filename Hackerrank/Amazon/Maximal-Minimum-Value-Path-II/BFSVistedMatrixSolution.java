import java.util.*;

class Solution {
    public static int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    
    public int maximumMinimumPath(int[][] grid) {
        int numRows = grid.length;
        int numColumns = grid[0].length;

        int maxMinVal = Math.min(grid[0][0], grid[numRows - 1][numColumns - 1]);
        int[] tracker = new int[1];

        while (maxMinVal >= 0) {
            if (pathExists(grid, maxMinVal, tracker)) {
                return maxMinVal;
            } else {
                maxMinVal = tracker[0];
            }
        }

        return -1;
    }

    public boolean pathExists(int[][] grid, int maxMinVal, int[] tracker) {
        int numRows = grid.length;
        int numColumns = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[numRows][numColumns];
        tracker[0] = 0;
        queue.offer(new int[]{0,0});
        while(!queue.isEmpty()) {
            int[] currentElement = queue.poll();
            if (currentElement[0] == numRows - 1 && currentElement[1] == numColumns - 1) {
                return true;
            }
            for (int[] direction: DIRECTIONS) {
                int nextRow = currentElement[0] + direction[0];
                int nextColumn = currentElement[1] + direction[1];
                
                if (0 <= nextRow && nextRow < numRows 
                    && 0 <= nextColumn && nextColumn < numColumns
                    ) {
                   int[] nextElement = new int[]{nextRow, nextColumn};
                    
                    if(grid[nextRow][nextColumn] >= maxMinVal) {                 
                        
                        if (!visited[nextElement[0]][nextElement[1]]) {
                            visited[nextElement[0]][nextElement[1]] = true;
                            queue.offer(nextElement);
                        }
                    } else if (!visited[nextElement[0]][nextElement[1]]) {
                        tracker[0] = Math.max(tracker[0], grid[nextRow][nextColumn]);
                    }
                }
            }
        }
    
        if (tracker[0] > 0) {
            tracker[0] = Math.min(tracker[0], maxMinVal);
        } else {
            tracker[0] = maxMinVal - 1;
        }

        return false;
    }
}