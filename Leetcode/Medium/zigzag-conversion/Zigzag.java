class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        int length = s.length();

        for (int i = 0; i * (2 *(numRows - 1)) < length; i++) {
            result.append(s.charAt(i * (2 *(numRows - 1))));
        }
        for (int j = 1; j < numRows - 1; j ++) {
            for (int i = 0; i * (2 *(numRows - 1)) < length; i++) {
                if (i * (2 *(numRows - 1)) + j < length) {
                    result.append(s.charAt(i * (2 *(numRows - 1)) + j));
                }
                if ((i + 1) * (2 *(numRows - 1)) - j < length) {
                    result.append(s.charAt((i + 1) * (2 *(numRows - 1)) - j));
                }
            }
        }
        for (int i = 0; (2 * i + 1) * (numRows - 1) < length; i++) {
            result.append(s.charAt((2 * i + 1) * (numRows - 1)));
        }
        return result.toString();
    }
}