class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        return RLE(countAndSay(n-1));
    }

    public String RLE(String s) {
        Deque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(1, s.charAt(0)));

        for (int i = 1; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == stack.peek().letter) {
                stack.peek().count++;
            } else {
                stack.push(new Pair(1, c));
            }
        }

        String result = "";
        while (!stack.isEmpty()) {
            Pair pair = stack.poll();
            result = Integer.toString(pair.count) + pair.letter + result;
        }
        return result;
    }

    static class Pair {
        int count;
        Character letter;

        public Pair(int count, Character letter) {
            this.count = count;
            this.letter = letter;
        }
    }
}