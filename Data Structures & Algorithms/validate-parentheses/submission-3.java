class Solution {
    public boolean isValid(String s) {

        // loop throught the string
        // if there is a opening bracket we will add it to the stack
        // if there is a closing bracket we will check 
        // stack if the opening matches the top of the stack we will pop it

        Stack<Character> stack = new Stack<>();

        for (char ch: s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // stack should have a opening char for this closing char
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if (ch == ')' && top != '(') {
                    return false;
                } else if (ch == ']' && top != '[') {
                    return false;
                } else if (ch == '{' && top != '{') {
                    return false;
                }
            }
        }


        // for (char ch : s.toCharArray()) {

        //     if (stack.isEmpty()) {
        //         stack.push(ch);
        //     } else if (ch == ')' && stack.peek() == '(') {
        //         stack.pop();
        //     } else if (ch == '}' && stack.peek() == '{') {
        //         stack.pop();
        //     } else if (ch == ']' && stack.peek() == '[') {
        //         stack.pop();
        //     } else {
        //         stack.push(ch);
        //     }
        // }   

        return stack.isEmpty();
    }
}
