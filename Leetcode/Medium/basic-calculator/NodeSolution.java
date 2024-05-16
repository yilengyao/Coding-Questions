class Solution {
    static class Node {
        String val;
        List<Node> children;

        public Node(List<String> sList) {
            if (sList.size() == 1) {
                this.val = sList.get(0);
                this.children = null;
            } else {
                this.val = null;
                this.children = initializeChildren(sList);
            }
        }

        public String getVal() {
            if (this.val != null) {
                System.out.println(this.val);
                return this.val;
            } else {
                Deque<Integer> calculations = new ArrayDeque<>();
                for (int i = 0; i < this.children.size(); i++) {
                    String token = this.children.get(i).getVal();
                    switch (token) {
                        case "*":
                            calculations.push(calculations.pop() * Integer.parseInt(this.children.get(i+1).getVal()));
                            i++;
                            break;
                        case "/":
                            calculations.push(calculations.pop() / Integer.parseInt(this.children.get(i+1).getVal()));
                            i++;
                            break;
                        case "+":
                            break;
                        case "-":
                            calculations.push(-1 * Integer.parseInt(this.children.get(i+1).getVal()));
                            i++;
                            break;
                        default:
                            calculations.push(Integer.parseInt(token));
                    }
                }   
                System.out.println(calculations);
                int answer = 0;
                while(!calculations.isEmpty()) {
                    answer += calculations.pop();
                }
                this.val = Integer.toString(answer);
                System.out.println(this.val);
                return this.val;
            }
        }

        private List<Node> initializeChildren(List<String> sList) {
            Deque<String> stack = new ArrayDeque<>();
            List<String> tmp = new ArrayList<>();
            List<Node> children = new ArrayList<>();
            for (String s: sList) {
                if (s.equals("(")) {
                    if (stack.size() > 0) {
                        tmp.add(s);
                    }
                    stack.push(s);
                } else if (s.equals(")")) {
                    stack.pop();
                    if (stack.size() > 0) {
                        tmp.add(s);
                    }
                } else {
                    tmp.add(s);
                }
    
                if (stack.isEmpty()){
                    children.add(new Node(tmp));
                    tmp = new ArrayList<>();
                }
            }
            return children;
        }
    }

    public int calculate(String s) {
        s = s.replace(" ", "");
        List<String> sList = Arrays.asList(s.split("(?=[()+\\-*/])|(?<=[()+\\-*/])"));
        Node answer = new Node(sList);
        return Integer.parseInt(answer.getVal());
    }

}