class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        // the logic is if I encounter number 
        // we will put it into the stack
        // if we got the operator we will poll the last two numbers 
        // and do the specific operations
        
        for (String str : tokens) {

            System.out.println(str.equals("+"));

            if (str.equals("+")) {
                System.out.println("anslnd");
                int num1 = stack.pop();
                int num2 = stack.pop();

                stack.push(num1 + num2);
            } else if (str.equals("-")) {
                int num1 = stack.pop();
                int num2 = stack.pop();

                stack.push(num2 - num1);
            } else if (str.equals("*")) {
                int num1 = stack.pop();
                int num2 = stack.pop();

                stack.push(num1 * num2);
            } else if (str.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();

                if (num2 == 0) {
                    stack.push(0);
                } else {
                    stack.push(num2 / num1);
                }
            } else {
                stack.push(Integer.parseInt(str));
            }
        }

        return stack.peek();
    }
}
