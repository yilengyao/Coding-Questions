class Solution {
    static int FIRST_CHAR = '@';
    public int titleToNumber(String columnTitle) {
        int sum = 0;
        int places = 1;
        for (int i = columnTitle.length() - 1; i >=0; i--) {
            sum += places * (columnTitle.charAt(i) - '@');
            places *= 26;
        }
        return sum;
    }
}