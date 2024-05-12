class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int todayPrice = prices[i];
            int yesterdayPrice = prices[i-1];
            if (todayPrice > yesterdayPrice) {
                profit += todayPrice - yesterdayPrice;
            }
        }
        return profit;
    }
}