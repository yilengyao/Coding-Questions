class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n <= 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] amount = new int[n];
        amount[0] = nums[0];
        amount[1] = nums[1];
        amount[2] = amount[0] + nums[2];
        for (int i = 3; i < n; i++) {
            amount[i] = Math.max(amount[i-2], amount[i-3])  + nums[i];
        }

        return Math.max(amount[n-1], amount[n-2]);
    }
}