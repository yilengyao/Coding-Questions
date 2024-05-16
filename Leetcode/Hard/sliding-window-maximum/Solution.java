class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] >= max) {
                maxIndex = i;
                max = nums[i];
            }
        }
        int[] result = new int[n-k+1];
        result[0] = max;
        for (int i = k; i < n; i++) {
            if (nums[i] >= max) {
                maxIndex = i;
                max = nums[i];
            }
            if (i - maxIndex >= k) {
                maxIndex = findMax(nums, i-k, i);
                max = nums[maxIndex];
            }
            result[i-k+1] = max;
        }

        return result;
    }

    int findMax(int[] nums, int left, int right) {
        int max = Integer.MIN_VALUE;
        int maxIndex = left;
        for (int i = left+1; i <= right; i++) {
            if (nums[i] >= max) {
                maxIndex = i;
                max = nums[i];
            }
        }
        return maxIndex;
    }
}