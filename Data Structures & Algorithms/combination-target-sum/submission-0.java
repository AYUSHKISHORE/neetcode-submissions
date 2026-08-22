class Solution {

    /*
        in combination or subset (order doesn't matter)
        [1,2,3] or [2,1,3] is a same thing need to avoid this
        in permutation [1,2,3] and [3,1,2] or [1,3,2] is different (order matters)

        so while doing combination
        we loop... value from i and backtrack i+1
            but if in combination (like combination sum ) we need repeated values 2,2,3
                we loop...value from i and backtrack i only

        where while doing permutation
        we loop... value  from 0 and backtrack without i
        
        //for subset and combination
            //for (int i = start; i < nums.length; i++)
        
        //permutation
            //for (int i = 0; i < nums.length; i++)
            //Because in permutation, at every position, you can choose any unused number.

            Subsets vs Combinations vs Permutations

            | Problem type    | Order matters? | Reuse allowed? | Common tool        |
            | --------------- | -------------: | -------------: | ------------------ |
            | Subsets         |             No |             No | `start` index      |
            | Combinations    |             No |     Usually no | `start` index      |
            | Permutations    |            Yes |             No | `used[]``start = 0`|
            | Combination Sum |             No |  Sometimes yes | `target` + `start` |

            Order matters means - [1,2] [2,1] are different result 
                Yes = means can be considered
                No = means cannot be considered


        Time 
            n = candidates.length
            T = target
            m = smallest value in candidates
            d = T / m   // maximum depth of recursion

            O(n^d)

        Space 
            O(k * T / m)
            k = valid combination
            T = target
            m = smallest value in candidates



            case 1: i=0 and backtrack(i) then we get [2,2,3] or [2,3,2] same value
            case 2: i=start and backtrack(i) then we get correct answer 
            case 3: i=0 and backtrack(i+1) then we get same answer as case 1
            case 4: i=start and backtrack(i+1) then we get only [7]

    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        int index = 0;
        backtrack(candidates, target, index, result, path);
        

        return result; 
    }

    public void backtrack(int[] candidates, int target, int index, List<List<Integer>> result, List<Integer> path){

        if(target == 0){
            result.add(new ArrayList<>(path));
            return;
        }

        if(target<0){
            return;
        }
        
        //in case of combination we loop from index to size where as in permutation we loop from 0 to size
        for(int i = index; i<candidates.length;i++){
            path.add(candidates[i]);
            //backtracking only i instead of i+1 as we might same value twice 
            //2,2,3
            backtrack(candidates, target-candidates[i], i, result, path);
            path.remove(path.size()-1);
        }

    }
}