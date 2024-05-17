class Solution {
    // Helper function to check if we can ship within the givne days with the given capacity
    boolean canShipWithinDays(int[] weights, int days, int capacity) {
        int currentWeight = 0;
        int requiredDays = 1;

        for(int weight: weights) {
            if (currentWeight + weight > capacity) {
                requiredDays++;
                currentWeight = 0;
            }
            currentWeight += weight;
            if (requiredDays > days) {
                return false;
            }
        }

        return true;
    }

    public int shipWithinDays(int[] weights, int day) {
        int n = weights.length;

        // Calculate the bounds for the binary search
        int low = 0;
        int high = 0;

        for (int weight: weights) {
            low = Math.max(low, weight); // minimum capacity should be at least the maximum weight
            high += weight; // maximum capacity could be the sum of all weights
        }

        // Perform binary search
        while (low< high) {
            int mid = low + (high - low) / 2;
            if (canShipWithinDays(weights, day, mid)) {
                high = mid; // try for a smaller capacity
            } else {
                low = mid + 1; // increase capacity
            }
        }

        return low;
    }
}