class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        // the logic is if I encounter number 
        // we will put it into the stack
        // if we got the operator we will poll the last two numbers 
        // and do the specific operations

        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        
        for (String str : tokens) {

            if (operators.contains(str)) {

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
                
            } else {
                stack.push(Integer.parseInt(str));
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
