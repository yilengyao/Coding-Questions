class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(' ) {
                    return false;
                } 
            } else if (c == '[') {
                stack.push(c);
            } else if (c == ']')  {           
                if (stack.isEmpty() || stack.pop() != '[' ) {
                    return false;
                } 
            } else if (c == '{') {
                stack.push(c);
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{' ) {
                    return false;
                } 
            }

        }

        if (stack.isEmpty()) return true;
        return false;
    }
}