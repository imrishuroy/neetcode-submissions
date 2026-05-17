class MinStack {

    ArrayDeque<Integer> stack;
    ArrayDeque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        int val = stack.pop();
        System.out.println(val);
        if (!minStack.isEmpty() && minStack.peek() == val) {
            minStack.pop();
        }

    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        } 

        return -1;
    }
}
