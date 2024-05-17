class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int capacity = 0;
        int max = 0;
        for (int w = 0; w< n ; w++) {
            capacity += weights[w];
            max = Math.max(max, weights[w]);
        }
        capacity = Math.max(capacity/days-1, max-1);
        int actualDays = Integer.MAX_VALUE;
        while (actualDays > days) {
            actualDays = 0;
            capacity++;
            int slack = capacity;
            int i = 0;
            while(i < n) {
                while (i < n && slack - weights[i] >= 0) {
                    slack -= weights[i];
                    i++;
                }
                actualDays++;
                slack = capacity;
            }
        }
        
        return capacity;
    }
