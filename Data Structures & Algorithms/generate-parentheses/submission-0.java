class Solution {


    /*

        Time Complexity 
            -> nos of valid string of parenthesis = O(Cn) {where Cn = catalan nos}
            -> Copying one string of length = O(2n)
            -> total = O(2n * Cn) = O(nCn)

        Space Complexity

            Computation Space
                -> Total Valid data store = O(Cn)
                -> Total Valid data to be copied in result = O(2n)
                -> total = O(Cn *2n) = O(nCn)

            Auxiliary Space
                -> Recursive stack = O(2n)
                -> StringBuilder   = O(2n) = O(n)
                Total = O(2n) + O(2n) = O(n)
        -> total =  O(n*Cn) + O(n) = O(nCn)


    */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        int open = 0;
        int close = 0;
        backtrack(result, n, open, close, new StringBuilder());
        return result;
    }

    public void backtrack(List<String> result, int n , int open, int close, StringBuilder current){

        if(open==n && close==n){
            result.add(current.toString());
        }

        if(open<n){
            current.append('(');
            backtrack(result,n,open+1,close,current);
            current.deleteCharAt(current.length()-1);
        }

        if(close<open){
            current.append(')');
            backtrack(result,n,open,close+1,current);
            current.deleteCharAt(current.length()-1);
        }

    }


}