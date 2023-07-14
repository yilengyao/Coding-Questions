class Solution {
    public boolean buddyStrings(String s, String goal) {
        int length = s.length();
        if (goal.length() != length) {
            return false;
        }
        if (s.equals(goal)) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < length; i++) {
                if (!set.add(s.charAt(i))) {
                    return true;
                }
            }
            return false;
        }
        int[] sDiffArray = new int[3];
        int[] goalDiffArray = new int[3];
        var diffIndex = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (diffIndex > 1) {
                    return false;
                }
                sDiffArray[diffIndex] = s.charAt(i);
                goalDiffArray[diffIndex] = goal.charAt(i);
                diffIndex++;
            }
        }
        return sDiffArray[0] == goalDiffArray[1] && sDiffArray[1] == goalDiffArray[0];
    }
}