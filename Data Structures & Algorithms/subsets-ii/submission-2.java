class Solution {

    /*
        Time
            - Sorting - O(nlogn)
            - Subset - O(2^n)
            - Copying in result - O(n) 

            total   = O(n) * O(2^n) + O(nlogn)
                    = O(n2^n)
        Space
            Auxiliary Space:
                Recursion stack = O(n)
                Path            = O(n)

                Total auxiliary space = O(n)


            Space including output:
                Number of subsets   = O(2^n)
                Elements per subset = up to O(n)

                Output space = O(n * 2^n)

            Total space including auxiliary = O(n * 2^n)

    */


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int index=0;
        backtrack(nums,index,path,result);
        return result;
    }

    public void backtrack(int[] nums, int index, List<Integer> path, List<List<Integer>> result){
        
            result.add(new ArrayList<>(path));

            for(int i=index;i<nums.length;i++){
                if(i>index && nums[i]==nums[i-1]){
                    continue;
                }
                path.add(nums[i]);
                backtrack(nums,i+1,path, result);
                path.remove(path.size()-1);
            }
    }
}