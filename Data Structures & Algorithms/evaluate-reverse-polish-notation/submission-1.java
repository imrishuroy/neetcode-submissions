class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        // the logic is if I encounter number 
        // we will put it into the stack
        // if we got the operator we will poll the last two numbers 
        // and do the specific operations
        
        for (String str : tokens) {

            if (isInteger(str)) {
                // System.out.println(str);
                stack.push(Integer.parseInt(str));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();

                int result = 0;

                switch (str) {
                    case "+":
                        result = num1 + num2;
                        System.out.println(str);
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num2 == 0 ? 0 : num2 / num1;
                        break;
                }

                stack.push(result);
            }
        }

        return stack.peek();
    }

    public boolean isInteger(String str) {
        if (str == null || str.isEmpty())  return false;

        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
