class PrefixTree {
    /*
        For everynode we are mainting a map<Character, TrieNode> and boolean (endOfString) to track whether that particular string has come to end or not.

        Time Complexity
            Insert - O(n)
            Search(both) - O(n)

        Space Complexity
            Insert - O(N)
            Search(both) - O(1)

    */
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfString;

        TrieNode(){
            children = new HashMap<>();
            endOfString = false;
        }

    }

    TrieNode root;
    public PrefixTree() {
         root = new TrieNode();
    }

    public void insert(String word) {
        
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
    
    public boolean search(String word) {

        TrieNode current = root;

        for(int i=0; i<word.length();i++){
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                return false;
            }
            current = node;
        }

        if(current.endOfString != true){
            return false;
        }
        return true;
    }
    
    public boolean startsWith(String prefix) {
        
        TrieNode current = root;
        for(int i=0; i<prefix.length();i++){
            char ch = prefix.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                return false;
            }
            current = node;
        }
        return true;
    }
}



/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
