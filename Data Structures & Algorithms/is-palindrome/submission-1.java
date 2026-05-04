class Solution {

    /*
        Logic 1
            just remove non alphanumeric characters
            s.replaceAll("[^a-zA-Z0-9]","")
            and normal palindromic check


        Logic 2
            at each character check if Character.isLetterOrDigit(char)
            handle the case also
            handle the continue scenario also
            a!#A -> fails if continue scenario is not there as it check even if i<j

    */
    public boolean isPalindrome(String s) {
        
        /*
        String newStr = s.replaceAll("[^a-zA-Z0-9]","");
        int i = 0, j=newStr.length()-1;
        while(i<j){
            if(Character.toLowerCase(newStr.charAt(i))!=Character.toLowerCase(newStr.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
        */

        char[] charArr = s.toCharArray();
        int i = 0, j = charArr.length-1;

        while(i<j){
            if(!Character.isLetterOrDigit(charArr[i])){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(charArr[j])){
                j--;
                continue;
            }
            if(Character.toLowerCase(charArr[i])!=Character.toLowerCase(charArr[j])){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
