class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        int l = 0;
        int r = 0;
        int sum = 0;
        int minLength = length + 1;
        while (r < length) {
            sum += nums[r++];
            while (sum >= target) {
                minLength = Math.min(minLength, r - l);
                sum -= nums[l++];
            }
        }
        return minLength > length ? 0 : minLength;
    }
}