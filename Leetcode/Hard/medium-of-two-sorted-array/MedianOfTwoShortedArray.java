class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        var length = nums1.length + nums2.length;
        var median = length / 2;

        if (length %2 == 0) {
            return (findOrdinal(median-1, nums1, nums2, 0, nums1.length-1, 0, nums2.length-1) + findOrdinal(median, nums1, nums2, 0, nums1.length-1, 0, nums2.length-1))/2.0;
        } else {
            return findOrdinal(median, nums1, nums2, 0, nums1.length-1, 0, nums2.length-1);
        }
    }


    private int findOrdinal(int ordinal, int[] nums1, int[] nums2, int nums1LeftPointer, int nums1RightPointer, int nums2LeftPointer, int nums2RightPointer) {
        if (nums1LeftPointer > nums1RightPointer) {
            return nums2[ordinal - nums1LeftPointer];
        }
        if (nums2LeftPointer > nums2RightPointer) {
            return nums1[ordinal - nums2LeftPointer];
        }

        var nums1Median = (nums1RightPointer + nums1LeftPointer) / 2;
        var nums2Median = (nums2RightPointer + nums2LeftPointer) / 2;

        if (nums1Median + nums2Median < ordinal) {
            if (nums1[nums1Median] < nums2[nums2Median]) {
                return findOrdinal(ordinal, nums1, nums2, nums1Median + 1, nums1RightPointer, nums2LeftPointer, nums2RightPointer);
            } else {
                return findOrdinal(ordinal, nums1, nums2, nums1LeftPointer, nums1RightPointer, nums2Median + 1, nums2RightPointer);
            }
        } else {
            if (nums1[nums1Median] < nums2[nums2Median]) {
                return findOrdinal(ordinal, nums1, nums2, nums1LeftPointer, nums1RightPointer, nums2LeftPointer, nums2Median - 1);
            } else {
                return findOrdinal(ordinal, nums1, nums2, nums1LeftPointer, nums1Median - 1, nums2LeftPointer, nums2RightPointer);
            }
        }
    }
}
