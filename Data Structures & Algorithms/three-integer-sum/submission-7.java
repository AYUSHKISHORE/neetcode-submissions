class Solution {
    /*

        time complexity - O(n^2)
        space complexity - O(n^2)
        Logic 
          i/p  [-1,0,1,2,-1,-4]
          we need to return the triplet which together make sum 0
          but need make sure result shouldn't contains duplicate output
          [-1, -1, 2] [-1, 0 , 1]

          1)sort the array
          2) loop across the array
            2.1) post sorting if 1st chosen element (here nums[i]) is greater than 0 then sum won't get 0 in any possibility break from loop.
            2.2) nums[i]==nums[i-1] when i>0 then skip/continue as it might lead to same result
            2.3) choose 2nd element at index i+1 and 3rd element at nums.length-1
                2.4) while(j<k)
                    2.5)check if sum of all element at these index is 0
                        2.6) if zero -> j++ k--;
                            also check while(j<k && nums[k]==nums[k-1]){
                                j++ 
                            }--> to skip same result from same i & same j
                        2.7) else based sum decide j++ or k--


          
    */
    public List<List<Integer>> threeSum(int[] nums) {
        

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){

            if(nums[i]>0){
                break;
            }

            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            int j = i+1;
            int k = nums.length-1;

            while(j<k){
                
                int val = nums[i]+nums[j]+nums[k];
                if(val>0){
                    k--;
                }else if(val<0){
                    j++;
                }else{
                    List<Integer> lst = new ArrayList<>();
                     res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                     j++;
                     k--;
                     while(nums[j]==nums[j-1] && j<k){
                        j++;
                     }
                }

            }

        }
        return res;
    }
}
