class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int result = length;

        for (int i = length-1; i >= 2; i--) {
            if (nums[i] == nums[i - 2]) {
                nums[i] = Integer.MAX_VALUE;
                length--;
            }
        }
        
        int left = 0;
        int right = 0;

        while (left < length) {
            if (nums[left] == Integer.MAX_VALUE) {
                right = Math.max(left, right);
                while (nums[right] == Integer.MAX_VALUE) {
                    right++;
                }
                swap(nums, left, right);
            }
            left++;
        }

        return length;
    }

    void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}