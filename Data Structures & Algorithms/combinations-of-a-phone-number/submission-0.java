class Solution {
    
    
       /*
            Time Complexity: O(n * 4^n)

            - At most 4^n combinations are generated.
            - Creating each final string takes O(n). -> result.add(path.toString());


            Space Complexity:

            1. Auxiliary Space: O(n)

            - Recursion stack: O(n)
            - StringBuilder path: O(n)
            - Mapping array: O(1)


            2. Output Space: O(n * 4^n)

            - At most 4^n strings are stored.
            - Each string has length n.


            Total Space: O(n * 4^n)
*/

    


    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length()==0){
            return result;
        }

        String[] mapping = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
        };

        int index = 0;
        StringBuilder path = new StringBuilder();
        backtrack(digits,result,index,mapping, path);
        return result;
    }

    public void backtrack(String digits, List<String> result, int index, String[] mapping, StringBuilder path){

        if(index==digits.length()){
            result.add(path.toString());
            return;
        }
        
        
        int digit = digits.charAt(index) - '0';
        // or we can write as 
        // int digit = Integer.parseInt(String.valueOf(digits.charAt(index)));
       
        String letters = mapping[digit];

        for(int i=0; i<letters.length();i++){
            path.append(letters.charAt(i));
            backtrack(digits,result,index+1,mapping,path);
            path.deleteCharAt(path.length()-1);
        }

    }
}