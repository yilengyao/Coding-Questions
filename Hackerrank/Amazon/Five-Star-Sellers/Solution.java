public class Solution {
    public static int fiveStarReviews(int[][] productRatings, int ratingsThreshold) {
        int n = productRatings.length;
        double p = ratingsThreshold/100.0;
        int count = 0;

        double cumulativeRating = cumulativeRating(productRatings);
        
        while( cumulativeRating < p * n) {

            int minPosition = findMaxIncrease(productRatings);
            int nominator = productRatings[minPosition][0];
            int denominator = productRatings[minPosition][1];
            
            cumulativeRating += ((nominator + 1.0) / (denominator  + 1.0) - (nominator + 0.0)/denominator);
            
            productRatings[minPosition][0] = nominator + 1;
            productRatings[minPosition][1] = denominator + 1;
            count++;

        };
        return count;
    }
    
    public static double cumulativeRating(int[][] productRatings) {
        double cumulativeRating = 0.0;
        for(int[] productRating: productRatings) {
            cumulativeRating += (productRating[0] +0.0)/productRating[1];
        }
        return cumulativeRating;
    }
    
    public static int findMaxIncrease(int[][] productRatings) {
        int position = 0;
        double maxIncrease = 0.0;
        for (int i=0; i < productRatings.length; i++) {
            int nominator = productRatings[i][0];
            int denominator = productRatings[i][1];
            double increase = (denominator - nominator)/((denominator + 1.0) * denominator);
            if(increase > maxIncrease) {
                maxIncrease = increase;
                position = i;
            }
        }
        return position;
    }
}