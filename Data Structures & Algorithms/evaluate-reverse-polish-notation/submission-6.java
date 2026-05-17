class Solution {
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b); // order doesn't matter for + and *
            } else if (token.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a); // careful: b is left operand
            } else if (token.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if (token.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a); // integer division truncates toward zero
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.peek();
    }
}
