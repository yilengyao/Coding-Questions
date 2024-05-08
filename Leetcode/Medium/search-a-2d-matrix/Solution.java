class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int numRows = matrix.length;
        int numColumns = matrix[0].length;
        int left = 0;
        int right = numRows * numColumns -1;


        int[] coordinate = new int[2];

        int medium = (right + left) / 2;
        while (left <= right) {
            medium = (right + left) / 2;
            flatten(medium, numColumns, coordinate);
            if(matrix[coordinate[0]][coordinate[1]] == target) {
                return true;
            } else if (matrix[coordinate[0]][coordinate[1]] < target) {
                left = medium + 1;
            } else {
                right = medium - 1;
            }
        }

        return false;
    }

    public void flatten(int position, int numColumns, int[] coordinate) {
        coordinate[0] = position / numColumns;
        coordinate[1] = position % numColumns;
    }
}