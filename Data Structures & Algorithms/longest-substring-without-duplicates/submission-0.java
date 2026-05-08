public class Solution {
    /*
        time complexity - O(n)
        space complexity - O(n)

        In sliding window 
         * whenever there is a ask of longest window then update the window after while because you want the largest possible answer

         * whenever there is a ask of smallest window then update the window inside while because you want to reduce as much as possible.


         Here we have Set to keep track of uniqueness.
         we move from right
            -> WHILE we found element exist in the set uniqueness end (TAKE THE CASE As this abcdbef here output is 5 but if we take if condition instead of while we get 6)
                -> then remove the left most element and increase left (AS WE NEED SUBSTRING)
            -> add the element in set
            -> update the maxLen


    NOTE - in sliding window always remove the leftmost element till duplication exist
    */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();

        int left = 0;
        int maxLen = 0;
        for(int right = 0; right<s.length();right++){
            
            char charCurr = s.charAt(right);
            while(set.contains(charCurr)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(charCurr);
            int currLen = right - left + 1;
            maxLen = maxLen>currLen?maxLen:currLen;
        }
        return maxLen;
    }
}