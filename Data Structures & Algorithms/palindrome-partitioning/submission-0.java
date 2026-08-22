class Solution {


    /*
        Time 
            Let take eg aabc => a_a_b_c
            for every n length of word we have n-1 position where we can break or continue
            total possibilites = O(2^n-1) ~= O(2^n)

            for copying each list in result (worst case max of length n) = O(n)

            palindrome function = O(n)

            total time = O(n * n * 2^n) ~= O(n^2 * 2^n)

        Space 
            Auxiliary Space 
                Recursive stack for word of max length = O(n)

            Computation space 
                result has (let say) k partition amd each partition has length n = O(kn)
                max partition we can have = 2^n
                total data stored in result = O(kn) ~= O(n2^n)

            Total space = O(n2^n) + O(n) = O(n2^n)

    */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();

        backtrack(s,path,result,0);

        return result;
    }

    public void backtrack(String s , List<String> path, List<List<String>> result, int start){
        
        if(start==s.length()){
            result.add(new ArrayList<>(path));
        }

        for(int end=start; end<s.length(); end++){
            if(isPalindrome(s,start,end)){
                path.add(s.substring(start,end+1));
                backtrack(s,path,result,end+1);
                path.remove(path.size()-1);

            }
        }

    }

    public boolean isPalindrome(String s, int start, int end){
        while(start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;

    }
}