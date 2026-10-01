class Solution {
    /*
        Here we have used 2 approaches 
            1 is basic backtracking 
            2 is trie + dfs + backtracking

            in 1st approach for every lets say [app, apple] we have to do the backtracking again all cells to find

            in 2nd approach 
                -> we first store the word in trie
                    eg -> a - p - p {app}
                          a - p - p - l - e {apple}

                    TrieNode has 2 variables
                        -> children
                        -> word 
                        (endOfString is not required in this case)
                
                -> we backtrack
                    -> inside backtrack if we found a word we store and eventually process and continue on storing the further word
                        eg app and apple

                    here
                        we do this 
                        backtrack(i+1,j) 
                        backtrack(i-1,j)
                        backtrack(i,j+1)
                        backtrack(i,j-1)

                        instead of 
                        backtrack(i+1,j)|| backtrack(i-1,j)||backtrack(i,j+1)||backtrack(i,j-1) 

                        mean going direction to find all possible word stored in trie
                        because if we go with or it will stop at particular value

                        app , aptl (it won't proceed aptl) we need process again for it

                
            Approach1
                Time - O(W × R × C × 4^L) --> W is words
                Space - O(L) ---> L length of word

            Approach2
                Time - O(R x C x 4^L)
                Space -
                    Recursion space - O(L)
                    Trie Space - O(S) -> nos o character in word
                    Result - O(Result)


    */


    public List<String> findWords(char[][] board, String[] words) {
        //return approachOne(board, words); // getting TLE
        return approachTwo(board, words);
    }

    class TrieNode{
        Map<Character, TrieNode> children;
        String word;
        TrieNode(){
            children = new HashMap<>();
            word = null;
        }
    }

    TrieNode root;

    public List<String> approachTwo(char[][] board, String[] words){
        
        List<String> result = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(String word : words){
            insert(word,root);
        }

        int n = board.length;
        int m = board[0].length;

        for(int i=0; i<n ;i++){
            for(int j=0; j<m;j++){
                backtrackApproachTwo(board,result,n,m,i,j,root);
            }
        }

        return result;

    }

    public void insert(String word, TrieNode root){
        
        TrieNode current=root;

        for(int i = 0; i<word.length();i++){
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                node = new TrieNode();
                current.children.put(ch,node);
            }

            current = node;
        }

        current.word = word;
    }

    public void backtrackApproachTwo(char[][] board, List<String> result, int n, int m, int r, int c, TrieNode current){

        if(r<0 || c<0 || r>=n || c>=m){
            return;
        }

        char ch = board[r][c];
        if(ch=='#'){
            return; //already visited cell
        }

        TrieNode node = current.children.get(ch);
        if(node == null){
            return;
        }

        if(node.word != null){
            result.add(node.word);

            node.word=null;
        }

        board[r][c]='#';

        backtrackApproachTwo(board, result, n, m , r+1, c, node);
        backtrackApproachTwo(board, result, n, m , r, c+1, node);
        backtrackApproachTwo(board, result, n, m , r-1, c, node);
        backtrackApproachTwo(board, result, n, m , r, c-1, node);

        board[r][c]=ch;
    }



    //
    //
    //
    //
    public List<String> approachOne(char[][] board, String[] words){
        List<String> result = new ArrayList<>();
       
        for(String word : words){
            int n = board.length;
            int m = board[0].length;
            int wordIndex = 0;
            boolean hasFound = false;
            for(int i = 0; i<board.length;i++){
                for(int j = 0;j<board[0].length;j++){
                    hasFound = backtrackApproachOne(i,j,word,board,n,m,wordIndex);
                    if(hasFound){
                        break;
                    }
                }
                if(hasFound){
                    break;
                }
            }
            if(hasFound){
                result.add(word);
            }
        }
        return result;
    }

    public boolean backtrackApproachOne(int r, int c, String word, char[][] board, int n, int m,int index){
        if(index == word.length()){
            return true;
        }

        if(r<0 || c<0 || r>=n || c>=m || board[r][c]!=word.charAt(index)){
            return false;
        }

        char temp = board[r][c];
        board[r][c]='.';
        boolean found = backtrackApproachOne(r+1,c,word,board,n,m,index+1)||backtrackApproachOne(r,c+1,word,board,n,m,index+1)||backtrackApproachOne(r-1,c,word,board,n,m,index+1)||backtrackApproachOne(r,c-1,word,board,n,m,index+1);

        board[r][c]=temp;
        return found;


    }
}