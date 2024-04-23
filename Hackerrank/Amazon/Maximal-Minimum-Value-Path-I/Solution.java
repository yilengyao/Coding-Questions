import java.util.*;

public class Solution {
	public int maxPathScore(int[][] matrix) {
	    int numRows = matrix.length;
	    int numCols = matrix[0].length;
		int[][] maxMinPath = new int[numRows + 1][numCols + 1];
		
		for (int row = 0; row <= numRows; row++) {
		    for (int col = 0; col <= numCols; col++) {
	            maxMinPath[row][col] = Integer.MIN_VALUE; 
		    }
		}	
		
		for (int row = 1; row <= numRows; row++) {
		    for (int col = 1; col <= numCols; col++) {
		        if (row == 1 && col == 1) {
		            maxMinPath[row][col] = matrix[0][0];
		        } else {
		            int currentValue = matrix[row-1][col-1];
		            int minTop = Math.min(maxMinPath[row-1][col], currentValue);
		            int minLeft = Math.min(maxMinPath[row][col-1], currentValue);
		            maxMinPath[row][col] = Math.max(minTop, minLeft);
		        }
		    }
		}
		return maxMinPath[numRows][numCols];
	}
}