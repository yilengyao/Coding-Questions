class Solution {
    public void sortColors(int[] nums) {
        int[] colorsFrequency = new int[3];
        for (int num: nums) {
            colorsFrequency[num]++;
        }
        
        int index = 0;
        for (int color = 0; color < colorsFrequency.length; color++) {
            for (int i = 0; i < colorsFrequency[color]; i++) {
                nums[index] = color;
                index++;
            }
        }
    }
}