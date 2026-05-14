class MinStack {
    /*
        Time Complexity = O(1)
        Space Complexity = O(N)
    
        Approach -
            Take 2 stacks
                1) one for operations
                2) second for keeping track of min element
    
        //Important case to consider in case of push val<= case
        //think of requirement push(2) push(2) pop() getMin
    
        MinStack uses two stacks: one normal stack stores all values, and another minStack stores the current minimum values. During push, we add the value to minStack only if it is smaller than or equal to the current minimum. During pop, if the removed value is equal to the current minimum, we remove it from minStack too, so getMin() always works in O(1).
        
    
    */
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        //Important to consider val<= case
        //think of requirement push(2) push(2) pop() getMin
        if (minStack.isEmpty() || (!minStack.isEmpty() && val <= minStack.peek())) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (stack.isEmpty())
            return;
        int top = stack.pop();
        if (top == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */