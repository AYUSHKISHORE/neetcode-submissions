class Solution {
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for(String token : tokens) {

            // operator
            if(token.equals("+") || token.equals("-") ||
               token.equals("*") || token.equals("/")) {

                int num1 = stack.pop();
                int num2 = stack.pop();

                if(token.equals("+")) {
                    stack.push(num2 + num1);
                }
                else if(token.equals("-")) {
                    stack.push(num2 - num1);
                }
                else if(token.equals("*")) {
                    stack.push(num2 * num1);
                }
                else {
                    stack.push(num2 / num1);
                }

            } else {

                // number
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}