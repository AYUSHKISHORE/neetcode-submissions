 class Solution {
/*
    Approach
        //Time complexity = O(n)
        //Space complexity = O(n)
        we can use either a hashMap or a counter array of type int to keep the track of character

        * we loop across the elements
            * track the element freq.
            * compute the maxFreq
            * compute the window (right - left + 1)
            * if(window-maxFreq>k)-> this means window has increase (i.e we more than k other elements which can't be replaced, hence increase from left)
                * update the character freq.
            * update the maxLen based on new window.


    This code solves the problem using sliding window. The idea is to maintain a window where at most k characters need to be replaced to make all characters in that window the same. For every character added from the right side, we update its frequency and keep track of the maximum frequency character in the current window. The number of replacements needed is calculated as window size - maxFreq. If this value becomes greater than k, it means the window is no longer valid, so we shrink the window from the left. During the process, we keep updating the maximum valid window length. In the end, that maximum length is the answer.

*/

    public int characterReplacement(String s, int k) {

        int[] freq = new int[26];
        int maxFreq = 0;
        int maxLen = 0;
        int left = 0;

        for(int right=0;right<s.length();right++){

                char curr = s.charAt(right);
                freq[curr-'A']++;
                maxFreq = Math.max(maxFreq,freq[curr-'A']);
                int windowSize = right - left + 1;
                if(windowSize - maxFreq > k){
                    char leftChar = s.charAt(left);
                    freq[leftChar-'A']--;
                    left++;
                }
                maxLen = Math.max(maxLen,right-left+1);

        }
        return maxLen;

    }
 }