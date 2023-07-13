class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        StringBuilder sPrime = new StringBuilder("#");
        for (char c: s.toCharArray()) {
            sPrime.append(c).append("#");
        }
        return manachersAlgorithm(sPrime.toString(), length*2+1).replace("#", "");

    }

    private String manachersAlgorithm(String s, int length) {
        int[] palindromeRadii = new int[length];
        int center = 0;
        int radius = 0;
        int maxRadius = 0;
        int maxIndex = 0;
        int mirror = 0;
        for (int i = 0; i < length; i++) {
            mirror = 2*center - i >= 0 ?  palindromeRadii[center + center - i] : 0;
            if (i + mirror < center + radius) {
                palindromeRadii[i] = mirror;
            } else {
                int newRadius = findRadius(s, length, i, Math.min(center + radius - i, mirror));
                palindromeRadii[i] = newRadius;
                if (i + newRadius >= center + radius) {
                    center = i;
                    radius = newRadius;
                    if (radius > maxRadius) {
                        maxRadius = radius;
                        maxIndex = center;
                    }
                }
            }
        }
        return s.substring(Math.max(0, maxIndex - maxRadius), Math.min(length, maxIndex + maxRadius+1));
    }

    private int findRadius(String s, int length, int center, int radius) {
        while (center - radius - 1 >= 0 && center + radius + 1 < length) {
            if (s.charAt(center + radius + 1 ) != s.charAt(center - radius - 1)) {
                break;
            }
            radius++;
        }
        return radius;
    }
}