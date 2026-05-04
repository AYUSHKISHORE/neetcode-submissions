class Solution {
    /*
    Logic - We store the elements in set
    let say our input is [2,20,4,10,3,4,5]
    
    # WRONG APPROACH
        if simply take a element 1by1
        2 then we find 2+1,2+2,....
        20 then we find 20+1 , 20+2...
        4 then we find 4+1, 4+2....
        in this way we keep searching for every element
        hence get TLE

    # RIGHT APPROACH
        take the edge element i.e element whose element - 1 doesn't exist
        here in our input [2,20,4,10,3,4,5]
        2 10 20 are edge element
        now we check for these element only if element +1 , element +2 ...exist

        thats why we are doing 
            if(!set.contains(val-1)){
                ....logic
            }

    */
    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for(int n : nums){
            set.add(n);
        }

        int maxCount = 0;
        for(int n : set){
            int val = n;
            if(!set.contains(val-1)){
                int count = 1;
                while(set.contains(val+count)){
                    count++;
                }
                if(maxCount<count){
                    maxCount=count;
                }
            }
            
        }
        return maxCount;

    }
}


