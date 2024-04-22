import java.util.*;

public class Solution {
	public int humanDays(int[][] matrix) {
		int numRows = matrix.length;
		int numColumns = matrix[0].length;

		Queue<int[]> uninfected = getUninfected(matrix);
		
		if (uninfected.size() == numRows * numColumns) {
		    return -1;
		}
		
		int days = 0;
		while(uninfected.size() > 0) {
		    outBreak(matrix, uninfected);
		    days++;
		}
		return days;
	}
	
	public Queue<int[]> getUninfected(int[][] matrix) {
	    int numRows = matrix.length;
	    int numColumns = matrix[0].length;
	    Queue<int[]> uninfected = new LinkedList<>();
	    
	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numColumns; col++) {
	            if (matrix[row][col] == 0) {
	                uninfected.offer(new int[]{row, col});
	            }
	        }
	    }
	    return uninfected;
	}
	
	public void outBreak(int[][] matrix, Queue<int[]>  uninfected) {
	    int numRows = matrix.length;
	    int numColumns = matrix[0].length;
	    Queue<int[]> infected = new LinkedList<>();
	    int n = uninfected.size();
	 	for (int candidates = 0; candidates < n; candidates++) {
	 	    int[] location = uninfected.poll();
	 	    int row = location[0];
	 	    int col = location[1];
	            if (row - 1 >= 0 
	            && matrix[row - 1][col] == 1 
	            && matrix[row][col] == 0) {
	                infected.offer(location);
	            } else if (col - 1 >= 0 
	            && matrix[row][col - 1] == 1 
	            && matrix[row][col] == 0) {
	                infected.offer(location);
	            } else if (row + 1 < numRows 
	            && matrix[row + 1][col] == 1
	            && matrix[row][col] == 0) {
	                infected.offer(location);
	            } else if (col + 1 < numColumns 
	            && matrix[row][col + 1] == 1
	            && matrix[row][col] == 0) {
	                infected.offer(location);
	            } else {
	                uninfected.offer(location);
	            }
	    }
	    
	    for(int[] zombie: infected) {
	        matrix[zombie[0]][zombie[1]] = 1;
	    }
	}
}