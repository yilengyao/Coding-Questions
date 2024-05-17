class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int maxRadius = 0;
        for(int house: houses) {
            int minRadius = Integer.MAX_VALUE;
            for(int heater: heaters) {
                minRadius = Math.min(minRadius, Math.abs(heater-house));
            }
            maxRadius= Math.max(maxRadius, minRadius);
        }

        return maxRadius;
    }
}