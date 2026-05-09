public class Solution {
    /*
        Time Complexity: O(n), where n is the length of s2
        Space Complexity: O(1), because we use fixed-size arrays of length 26


        Approach
            This is a fixed-size sliding window problem where I compare character frequencies instead of generating permutations, which makes the solution efficient.

            Here the approach is pretty simple we compare the freq in a fixed size window of array instead of generating permutation

            * we keep has 2 array of size26 for storing character index of both array's freq
            * Now we loop on s1 and generate the freq for s1's character and s2's 1st window
            * we now compare the Arrays.equals(s1'sFreq,s2's_1st_window'sFreq) if matches return true

            * if matches are false -> now we loop across s2 starting from index = s1.length() -> as we already checked 1st window
                * now add new right (which is right = s1.length)
                * remove the left
                * compare the array if matchres return true;
            * else return false






    */
    public boolean checkInclusion(String s1, String s2) {
       
       if(s1.length()> s2.length()){
            return false;
       }

       int charCounters1[] = new int[26];
       int charS2[] = new int[26];

       for(int i = 0;i<s1.length();i++){
            char s1Curr = s1.charAt(i);
            charCounters1[s1Curr-'a']++;
            //Filling the 1st window of charS2Window
            char s2Curr = s2.charAt(i);
            charS2[s2Curr-'a']++;
       }

       if(Arrays.equals(charCounters1,charS2)==true){
            return true;
       }

        int windowSize = s1.length();
        //Since 1st window is already consider we move from 
       for(int right = windowSize;right<s2.length();right++){
            //add new index from right
            int newWindow = s2.charAt(right)-'a';
            charS2[newWindow]++;

            //now we exclude a index from left (i.e remove the left index)
            int leftIndex = right-windowSize;
            int oldWindow =s2.charAt(leftIndex)-'a';
            charS2[oldWindow]--;
            
            //do the array conversion
            if(Arrays.equals(charCounters1,charS2)){
                return true;
            }
       }

       return false;
    }
}