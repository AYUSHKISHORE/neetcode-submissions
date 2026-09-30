class WordDictionary {


    /*
        Logic
            To search a word especially .ad or b.. we take children of current node 
                i.e current.children.get and check each children value which of them is matching

        Time Complexity 
            Insert - O(N)
            Search - O(N^L) where N is required length of word and L is length of all the applicable children (L = 26)

        Space 
            Insert - O(N)
            Search - O(N) recursion stack
    */
    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endOfString;

        TrieNode(){
            children = new HashMap<>();
            endOfString=false;
        }
    }

    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode current = root;
        for(int i = 0; i<word.length();i++){
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                node = new TrieNode();
                current.children.put(ch,node);
            }
            current=node;
        }
        current.endOfString = true;
    }

    public boolean wordSearch(String word, int index, TrieNode root){

        if(index==word.length()){ // not word.length-1 because root is dummy kind
            return root.endOfString;
        }

        char ch = word.charAt(index);
        if(ch!='.'){ // if first character is not . --> go for normal recursion
            TrieNode node = root.children.get(ch);
            if(node == null){
                return false;
            }
            return wordSearch(word,index+1,node);
        }else{
            for(TrieNode node : root.children.values()){
                if(wordSearch(word,index+1,node)){ // if condition there to check all children
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean search(String word) {
        return wordSearch(word,0,root);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */