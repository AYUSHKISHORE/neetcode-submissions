class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer, Integer> map = new HashMap<>();

        for(int n : nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }

        //creating an array of lists.
        List<Integer>[] bucket = new List[nums.length+1]; //as we are storing upto n elements
        for(int i = 0; i<=nums.length;i++){
            // Initialize each bucket so we can safely add elements later
            bucket[i]= new ArrayList<>();
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            bucket[entry.getValue()].add(entry.getKey());
        }

        int res[] = new int[k];
        int index = 0;
        //since we stored data in as value , so max value comes last and we last k elements
        for(int i = bucket.length-1; i>=0 && index<k; i--){

            for(int n : bucket[i]){
                res[index++]=n;
                if(index==k){
                    return res;
                }
            }
        }

        return res;


    }
}
