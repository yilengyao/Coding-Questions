class Solution {
    public void sortColors(int[] nums) {
        int redIndex = 0;
        int whiteIndex = 0;
        int blueIndex = nums.length - 1;

        while (whiteIndex <= blueIndex) {
            if (nums[whiteIndex] == 0) {
                swap(nums, redIndex, whiteIndex);
                redIndex++;
                whiteIndex++;
            } else if (nums[whiteIndex] == 2) {
                swap(nums, whiteIndex, blueIndex);
                blueIndex--;
            } else if (nums[whiteIndex] == 1) {
                whiteIndex++;
            }
        }
    }

    public void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}