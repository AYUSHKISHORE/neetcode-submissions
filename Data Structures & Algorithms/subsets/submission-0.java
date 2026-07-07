class Solution {

    /*

        Backtracking = try one choice → go deeper → undo that choice → try next choice.

        template:
            void backtrack(...) {
                if (base condition) {
                    // save answer
                    return;
                }

                for (choice : choices) {
                    // 1. choose
                    // 2. explore
                    // 3. undo
                }
            }

        More clear template
            void backtrack(List<Integer> path) {
                if (some condition) {
                    result.add(new ArrayList<>(path));
                    return;
                }

                for (int i = 0; i < choices.length; i++) {
                    path.add(choices[i]);      // choose
                    backtrack(path);           // explore
                    path.remove(path.size()-1); // undo
                }
            }
            
        Recursion Tree

            []
            ├── [1]
            │   ├── [1, 2]
            │   │   └── [1, 2, 3]
            │   └── [1, 3]
            ├── [2]
            │   └── [2, 3]
            └── [3]


        Time = O(n * 2^n)
            2^n = nos of subset
            n = for copying the path

        Space = O(n * 2^n)
            n = recursion can go upton
            2^n = result list
    
        



    */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        int index = 0;
        backtrack(nums,index, path, result);
        return result;
    }

    public void backtrack(int[] nums, int index, List<Integer> path, List<List<Integer>> result){
        result.add(new ArrayList<>(path)); //creates a copy of path.
        //if we write result.add(path) it keep on adding in same set not subsets
        //then every subset in result would point to the same path object, and the final answer would be wrong.

        for(int i = index; i<nums.length;i++){
            path.add(nums[i]);
            backtrack(nums,i+1,path, result);
            path.remove(path.size()-1);
        }

    }
}