class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int[] cdf = new int[length + 1];
        cdf[0] = nums[0]; 
        for (int i = 1; i < length + 1; i++) {
            cdf[i] = nums[i - 1] + cdf[i-1];
        }
        int l = 0;
        int r = 1;
        int minLength = length + 1;
        while (r < length + 1) {
            if (cdf[r] - cdf[l] < target) {
                r++;
            } else {
                while (cdf[r] - cdf[l] >= target) {
                    minLength = Math.min(minLength, r - l);
                    l++;
                }
            }
        }
        return minLength > length ? 0 : minLength;
    }
}