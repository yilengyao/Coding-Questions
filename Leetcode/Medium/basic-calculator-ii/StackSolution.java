class Solution {
    static Map<String, String> OPERATIONS = Map.of(
        "\\+", "+",
        "\\-", "-",
        "\\*", "*",
        "\\/", "/"
    );
    
    public int calculate(String s) {
        s = s.replace(" ", "");
        Deque<String> stack = new ArrayDeque<>();
        stack.push(s);

        for (String operation: OPERATIONS.keySet()) {
            Deque<String> tmpStack = new ArrayDeque<>();

            while(!stack.isEmpty()) {
                String[] sArray = stack.pop().split(operation);
                tmpStack.push(sArray[0]);
                for (int i = 1; i < sArray.length; i++) {
                    tmpStack.push(OPERATIONS.get(operation));
                    tmpStack.push(sArray[i]);
                }
            }
            
            while (!tmpStack.isEmpty()) {
                stack.push(tmpStack.pop());
            }        
        }
        
        Deque<String> tmpStack = new ArrayDeque<>();
        while(!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals("*") || current.equals("/")) {
                int prev = Integer.parseInt(stack.pop());
                int next = Integer.parseInt(tmpStack.pop());
                int num = current.equals("*") ? prev * next : next / prev;
                tmpStack.push(Integer.toString(num));
            } else {
                tmpStack.push(current);
            }
        }

        while (!tmpStack.isEmpty()) {
            stack.push(tmpStack.pop());
        }     

        while(!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals("+") || current.equals("-")) {
                int prev = Integer.parseInt(stack.pop());
                int next = Integer.parseInt(tmpStack.pop());
                int num = current.equals("+") ? prev + next : next - prev;
                tmpStack.push(Integer.toString(num));
            } else {
                tmpStack.push(current);
            }
        }
        stack = tmpStack;

        return Integer.parseInt(stack.poll());
    }
}