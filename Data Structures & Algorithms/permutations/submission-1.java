class Solution {

    /*
        In permutation means ordering/arrangement and combination means selection/grouping

        So in permutation we might need same element again
        
        * for(i=0) -> always and we are keeping track of used element in path by boolean tracker
        and no need to pass the value in backtrack call

        * backtrack() (not backtrack(i) or backtrack(i+1))

        time complexity 
            -> O(n * nPr)
            where n = for copying n element for each n! in result
                if (current.size() == nums.length) {
                    result.add(new ArrayList<>(current)); // O(n) copy happens here
                    return;
                }
            where nPr = (n!)/(n-r)! => (n==r) here so => n!/0! and (note - 0! is 1)
            here when n==r => nPr = n!

            final time complexity = O(n * n!)

        space complexity

            Auxiliary Space:
                Recursion stack = O(n)
                Path            = O(n)
                Used array      = O(n)

                Total auxiliary space = O(n)


            Space including output:
                n! permutations (where nPr = (n!)/(n-r)! => (n==r) here so => n!/0! and (note - 0! is 1)
)
                n elements per permutation ==> result.add(new ArrayList<>(path));

                Total = O(n * n!)

            Total Space = O(n) + O(n * n!) = O(n * n!)

     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, result, path, used);
        return result;
    }

    public void backtrack(int[] nums, List<List<Integer>> result, List<Integer> path, boolean[] used){

            if(path.size()==nums.length){
                result.add(new ArrayList<>(path));
            }

            for(int i = 0; i<nums.length; i++){
                
                if(used[i]){
                    continue;
                }

                //add               
                used[i]=true;
                path.add(nums[i]);
                
                //backtrack
                backtrack(nums,result,path,used);
                
                //remove
                used[i]=false;
                path.remove(path.size()-1);
            }

    }
}