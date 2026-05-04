class Solution {

    /*
    Logic 
        loop across the matrix
        create a row Map storing element as set
        create a col Map storing element as set
        ** for each square create Map storing element as set
            each square has unique key = r/3+","c/3

    time complexity - O(n^2)
    space complexity - O(n^2)


    putIfAbsent(key,value) vs computeIfAbsent(key,value)
    putIfAbsent -> it creates the value even if key exist (not insert)
    computeIfAbsent -> it creates the value ONLY if key doesn't exist

    putIfAbsent(key, value)
        → adds value only if key is absent
        → value is created eagerly (always created)

    computeIfAbsent(key, function)
        → adds value only if key is absent
        → value is created lazily (only when needed)

    Map<String, String> map = new HashMap<>();
    map.put("name", "Alice");
    map.putIfAbsent("name", new String("Bob"));
    {
        "name" already exists → "Alice"
        "Bob" is still created in memory
         But map does NOT change
    }
    Final Map = {name=Alice}

    map.computeIfAbsent("name", k -> {
        return "Bob";
    });
    
    {
        "name" exists → function is NOT executed
        "Bob" is never created
    }
     Final Map = {name=Alice}
    */
    public boolean isValidSudoku(char[][] board) {
        
        Map<Integer, HashSet<Character>> row = new HashMap<>();
        Map<Integer, HashSet<Character>> col = new HashMap<>();

        Map<String, HashSet<Character>> square = new HashMap<>();

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++){

                if(board[r][c]=='.'){
                    continue;
                }

                String squareKey = r/3+","+c/3;
                row.computeIfAbsent(r,k->new HashSet<>());
                col.computeIfAbsent(c,k->new HashSet<>());
                square.computeIfAbsent(squareKey,k->new HashSet<>());
                if(row.get(r).contains(board[r][c]) || col.get(c).contains(board[r][c]) || square.get(squareKey).contains(board[r][c])){
                    return false;
                }
                row.get(r).add(board[r][c]);
                col.get(c).add(board[r][c]);
                square.get(squareKey).add(board[r][c]);
            }
        }
        return true;
    }
}
