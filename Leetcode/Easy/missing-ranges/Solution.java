class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> range = new ArrayList<>();
        if (nums.length == 0) {
            range.add(List.of(lower, upper));
            return range;
        }

        if (lower < nums[0]) {
            range.add(List.of(lower, nums[0]-1));
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 <= nums[i+1] -1) {
                range.add(List.of(nums[i] + 1, nums[i+1]-1));
            }
        }

        if (nums[nums.length-1] < upper) {
            range.add(List.of(nums[nums.length-1]+1, upper));
        }

        return range;
    }
}