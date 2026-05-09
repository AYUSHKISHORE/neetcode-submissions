public class Solution {

    /*
        Time Complexity: O(n + m)
        Space Complexity: O(n + m) generally
        n = s.length()
        m = t.length()

        Approach 
            -> It is a simple algorithm kind of variable size sliding window
            -> check the base condition if t.length>s.length || t.length==0 return ""
            -> we created 2 maps 
                -> 1st to hold the count of element in t
                -> 2nd to keep the track of window elements count
            
            -> define variables like 
                -> have = to track the window variable
                -> needCount = 1st map size
                -> minLen = to hold valid length
                -> startIndex = to hold the startIndex of valid length
                -> left = 0

            -> Loop over 2nd string
                -> add the character in 2nd map (if present increase count)
                -> check if 1st map contains the character and count of both map[character] is same 
                    -> then increase have
                -> while have == needSize
                    -> get windowSize = right - left + 1;
                    -> update the minLen if windowSize<minLen 
                        -> update the startIndex
                    -> get the leftMost character
                    -> reduced the leftMost character count
                    -> check if 1st map need leftMost character && countOf2ndMap[leftCharacter]<countof1stMap[leftCharactet]
                        -> reduce have--
                
                -> loop ends
                -> minLen == Integer.MAX_VALUE return ""
                -> else return s.susbstring(startIndex, startIndex + minLen)

        See below example a very good for understanding why reducing left is required inside while.
        S = "AABC"
        T = "ABC"

        if we don't reduce left in while we get string AABC as output instead of ABC.
    */
    public String minWindow(String s, String t) {
       
        if(t.length()==0 || t.length()>s.length()){
            return "";
        }

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character,Integer> window = new HashMap<>();

        for(int i = 0; i < t.length(); i++){
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i),0)+1);
        }

        int left = 0;
        int startIndex = 0;
        int have = 0;
        int needCount = need.size(); // have counter is already increasing post checking the variable count
        int minLen = Integer.MAX_VALUE;

        for(int right = 0;right<s.length();right++){
            char curr = s.charAt(right);
            window.put(curr,window.getOrDefault(curr,0)+1);

            if(need.containsKey(curr) && window.get(curr).intValue()==need.get(curr).intValue()){
                have++;
            }

            while(have==needCount){

                int windowSize = right - left + 1;

                if(windowSize<minLen){
                    minLen = windowSize;
                    startIndex = left;
                }

                //now we check whether removing left most element is will give same result or not
                char leftChar = s.charAt(left);
                window.put(leftChar, window.getOrDefault(leftChar,0)-1);
                left++;
                //now we will check whether removing left most char still gives correct result or not; if not get off the loop
                if(need.containsKey(leftChar) && window.get(leftChar)<need.get(leftChar)){
                    have--;
                }
            }
        }

        if(minLen== Integer.MAX_VALUE){
            return "";
        }

        return s.substring(startIndex, startIndex+minLen);
    }
}