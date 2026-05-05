class Solution {

    //time complexity = O(N)
    //space complexity = O(1)
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length-1;

        while(i<=j){
            if(numbers[i]+numbers[j]==target){
                return new int[]{i+1,j+1};
            }
            if(numbers[i]+numbers[j]>target){
                j--;
                continue;
            }
            if(numbers[i]+numbers[j]<target){
                i++;
                continue;
            }
        }
        return new int[]{};
    }
}
