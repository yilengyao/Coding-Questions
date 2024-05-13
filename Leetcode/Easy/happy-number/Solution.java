class Solution {
    public boolean isHappy(int n) {
        if (n == 1) return true;
        Set<Integer> visitedNumbers = new HashSet<>();

        while (n != 1) {
            int number = 0;
            while (n != 0) {
                number += (n%10) * (n%10);
                n = n/10;
            }
            if (visitedNumbers.contains(number)) {
                return false;
            }
            visitedNumbers.add(number);
            n = number;
        }
        return true;
    }
}