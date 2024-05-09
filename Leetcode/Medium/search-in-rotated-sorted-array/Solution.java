class Solution {
    public int search(int[] nums, int target) {
        int length = nums.length;

        int pivot = findMin(nums);

        int left = 0;
        int right = length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[rightShift(mid, pivot, length)] == target) {
                return rightShift(mid, pivot, length);
            } else if (nums[rightShift(mid, pivot, length)] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int rightShift(int index, int pivot, int length) {
        return (index + pivot) % length;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}