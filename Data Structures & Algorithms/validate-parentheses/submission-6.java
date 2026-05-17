class Solution {
    public boolean isValid(String s) {
        
        Stack<Character> stack = new Stack();

        for (char ch : s.toCharArray()) {
            // check if this is opening character
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                // check if stack is empty, return false
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (ch == ')' && top != '(') {
                    return false;
                } else if (ch == ']' && top != '[') {
                    return false; 
                } else if (ch == '}' && top != '{') {
                    return false;
                }
                
            }
        }

        return stack.isEmpty();
    }
}
