import java.util.*;


public class Solution {
     static class Product implements Comparable<Product> {
         int fiveStarReviews;
         int totalReviews;
         
         public Product(int fiveStarReviews, int totalReviews) {
             this.fiveStarReviews = fiveStarReviews;
             this.totalReviews = totalReviews;
         }
         
         public int getFiveStarReviews() {
             return this.fiveStarReviews;
         }
         
         public int getTotalReviews() {
             return this.totalReviews;
         }
         
         public double getRating() {
             return 1.0 * this.fiveStarReviews / this.totalReviews;
         }
         
         public double getGain() {
             return (this.fiveStarReviews + 1.0) / (this.totalReviews + 1.0) - this.getRating();
         }
         
         
         @Override
         public int compareTo(Product otherProduct) {
             return Double.compare(otherProduct.getGain(), this.getGain());
         }
         
     }


    public static int fiveStarReviews(int[][] productRatings, int ratingsThreshold) {
        int n = productRatings.length;
        double p = ratingsThreshold/100.0;
        int count = 0;
        PriorityQueue<Product> maxHeap = new PriorityQueue<>();

        double cumulativeRating = cumulativeRating(productRatings, maxHeap);
        
        while( cumulativeRating < p * n) {
            Product product = maxHeap.poll();
                        
            cumulativeRating += product.getGain();
            
            maxHeap.add(new Product(product.getFiveStarReviews() + 1, product.getTotalReviews() + 1));
            count++;

        };
        return count;
    }
    
    public static double cumulativeRating(int[][] productRatings, PriorityQueue<Product> maxHeap) {
        double cumulativeRating = 0.0;
        for(int[] productRating: productRatings) {
            Product product = new Product(productRating[0], productRating[1]);
            cumulativeRating += product.getRating();
            maxHeap.add(product);
        }
        return cumulativeRating;
    }
    
}