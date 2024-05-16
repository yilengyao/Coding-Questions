class Solution {

    public int calculate(String s) {
        s = s.replace(" ", "");
        List<String> sList = Arrays.asList(s.split("(?=[()+\\-*/])|(?<=[()+\\-*/])"));
        Deque<String> tokens = new ArrayDeque();
        for (String token: sList) {
            if (token.equals(")")) {
                Deque<Integer> calculations = new ArrayDeque();
                String current = tokens.poll();
                while(!current.equals("(")) {
                    switch (current) {
                        case "*":
                            int next = calculations.poll();
                            int prev = Integer.parseInt(tokens.poll());
                            calculations.push(prev*next);
                            break;
                        case "/":
                            next = calculations.poll();
                            prev = Integer.parseInt(tokens.poll());
                            calculations.push(prev*next);
                            break;
                        case "-":
                            calculations.push(-1 * calculations.poll());
                            break;
                        case "+":
                            break;
                        default:
                            calculations.push(Integer.parseInt(current));
                    }
                    current = tokens.poll();
                }
                int sum = 0;
                while(!calculations.isEmpty()) {
                    sum += calculations.poll();
                }
                tokens.push(Integer.toString(sum));
            } else {
                tokens.push(token);
            }
        }

        Deque<Integer> calculations = new ArrayDeque();
        while(!tokens.isEmpty()) {
            String current = tokens.poll();
            switch (current) {
                case "*":
                    int next = calculations.poll();
                    int prev = Integer.parseInt(tokens.poll());
                    calculations.push(prev*next);
                    break;
                case "/":
                    next = calculations.poll();
                    prev = Integer.parseInt(tokens.poll());
                    calculations.push(prev*next);
                    break;
                case "-":
                    calculations.push(-1 * calculations.poll());
                    break;
                case "+":
                    break;
                default:
                    calculations.push(Integer.parseInt(current));
            }
        }
    
        int sum = 0;
        while(!calculations.isEmpty()) {
            sum += calculations.poll();
        }
        return sum;
    }
}