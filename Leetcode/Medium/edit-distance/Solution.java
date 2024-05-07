class Solution {
    public int minDistance(String word1, String word2) {
        word1 = " " + word1;
        word2 = " " + word2;
        int rowLength = word1.length();
        int columnLength = word2.length();

        int[][] lD = new int[rowLength][columnLength]; // Levenstein Distance

        for (int row = 1; row < rowLength; row++) {
            int editOccur = word1.charAt(row) == word2.charAt(0) ? 0 : 1; 
            lD[row][0] = lD[row - 1][0] + editOccur;
         }

         for (int column = 1; column < columnLength; column++) {
            int editOccur = word1.charAt(0) == word2.charAt(column) ? 0 : 1;
            lD[0][column] = lD[0][column - 1] + editOccur;
         }

         for (int row = 1; row < rowLength; row++) {
            for (int column = 1; column < columnLength; column++) {
                if (word1.charAt(row) == word2.charAt(column)) {
                    lD[row][column] = lD[row - 1][column - 1];
                } else {
                    lD[row][column] = Math.min(Math.min(lD[row - 1][column], lD[row][column - 1]), lD[row - 1][column - 1]) + 1;
                }
            }
        }

         return lD[rowLength-1][columnLength-1];
    }
}