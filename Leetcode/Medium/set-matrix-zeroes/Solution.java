class Solution {
    public void setZeroes(int[][] matrix) {
        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        List<int[]> zeros = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                if (matrix[row][column] == 0) {
                    zeros.add(new int[]{row, column});
                }
            }
        }

        for (int[] zero: zeros) {
            for (int column = 0; column < numColumns; column++) {
                matrix[zero[0]][column] = 0;
            }
            for (int row = 0; row < numRows; row++) {
                matrix[row][zero[1]] = 0;
            }
        }
    }
}