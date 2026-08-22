class Solution {

    /*
        backtrackApproachOne
            - Not good approach as few cases got TLE
            - Normal combination sum 
                -  copy path to new arrayList (not point to same path)
                -  if we do arr = path if doesn't copy path ...it points to same list
                -  in this approach we copy path to arr and sort the arr and then store it result

            - Time 
                - sorting O(nlogn)
                - storing - O(n)
                - backtracking - O(2^n)
                - contains - O(n *r) (r = nos of list inside r)

                sorting + (backtrack of n ) + contains
                O(nlogn) + O(n*2^n) + O(n*r)

            - Space
                path - O(n)
                result - O(2^n)
                total - O(n*2^n)

        backtrackApproachTwo
            In this approach we first sort the number 
                to avoid duplicates we check a[i]==a[i-1] then continue

            Time 
               sort - O(nlogn)
               logic - O(n*2^n)
               total =  O(nlogn) + O(n*2^n) = O(n*2^n)
            Space
                path = O(n) 
                result = O(2^n)
                total = O(n*2^n)




    */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int index = 0;
        //backtrackApproachOne(candidates,target,path,result,index); //TLE for 4 cases
        Arrays.sort(candidates);
        backtrackApproachTwo(candidates,target,path,result,index);

        return result;    
    }


    public void backtrackApproachOne(int[]candidates, int target, List<Integer> path,List<List<Integer>> result, int index){

        if(target == 0){
            List<Integer> arr = new ArrayList<>(path);
            //if we do arr = path if doesn't copy path ...it points to same list
            Collections.sort(arr);
            if(result.contains(arr)){
                return;
            }
            result.add(new ArrayList<>(arr));
            return;
        }

        if(target<0){
            return;
        }

        for(int i = index; i<candidates.length;i++){
            path.add(candidates[i]);
            backtrackApproachOne(candidates,target-candidates[i],path,result,i+1);
            path.remove(path.size()-1);
        }

    }

    public void backtrackApproachTwo(int[]candidates, int target, List<Integer> path,List<List<Integer>> result, int index){
        if(target == 0){
            result.add(new ArrayList<>(path));
            return;
        }

        if(target<0){
            return;
        }

        for(int i = index; i<candidates.length;i++){
            if(i>index && candidates[i]==candidates[i-1]){
                continue;
            }
            if(candidates[i]>target){
                break;
            }
            path.add(candidates[i]);
            backtrackApproachTwo(candidates,target-candidates[i],path,result,i+1);
            path.remove(path.size()-1);
        }

    }

}