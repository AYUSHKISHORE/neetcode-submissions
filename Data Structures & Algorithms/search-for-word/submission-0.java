class Solution {


    /*
        Time Complexity
            - O(n * m * 4^L)
            - 4^L has we have 4 direction to find L

        Space Complexity 
            - O(L) = length of word

    */


    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        int wordIndex = 0;
        boolean hasFound = false;
        for(int i=0; i<n ; i++){
            for(int j=0; j<m; j++){
                hasFound = backtrack(board,i,j,wordIndex, word);
                if(hasFound){
                    return hasFound;
                }
            }
        }
        return hasFound;
    }

    public boolean backtrack(char[][]board, int r, int c, int index, String word){

        if(word.length() == index){
            return true;
        }

        if(r<0 || c<0 || r>=board.length || c>=board[0].length || board[r][c]!=word.charAt(index)){
            return false;
        }
        char temp = board[r][c];
        board[r][c]='.';

        boolean found = backtrack(board,r+1,c,index+1,word) || backtrack(board,r-1,c,index+1,word) || backtrack(board,r,c+1,index+1,word) || backtrack(board,r,c-1,index+1,word);

        board[r][c]=temp;
        return found;

    }
}