class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {

        Map<Character, Integer> characterCountSlidingWindow = new HashMap<>();
        int left = 0;
        int maxWidth = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            characterCountSlidingWindow.put(rightChar, characterCountSlidingWindow.getOrDefault(rightChar, 0) + 1);


            while (characterCountSlidingWindow.size() > 2) {
                char leftChar = s.charAt(left);
                int characterCount = characterCountSlidingWindow.get(leftChar);

                if (characterCount > 1) {
                    characterCountSlidingWindow.put(leftChar, characterCount - 1);
                } else {
                    characterCountSlidingWindow.remove(leftChar);
                }
                left++;
            }
            maxWidth = Math.max(maxWidth, right - left +1);
        }

        return maxWidth;
    }
}