class Solution {
    public int calculate(String s) {


        s = s.replace(" ", "");
        String[] tokens = s.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        Deque<Integer> calculations = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            switch (token) {
                case "*":
                    calculations.push(calculations.pop() * Integer.parseInt(tokens[i+1]));
                    i++;
                    break;
                case "/":
                    calculations.push(calculations.pop() / Integer.parseInt(tokens[i+1]));
                    i++;
                    break;

                case "+":
                    break;

                case "-":
                    calculations.push(-1 * Integer.parseInt(tokens[i+1]));
                    i++;
                    break;
                default:
                    calculations.push(Integer.parseInt(token));
            }
        }

        int answer = 0;
        while(!calculations.isEmpty()) {
            answer += calculations.pop();
        }
        return answer;
    }
}