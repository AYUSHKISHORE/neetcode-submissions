class Solution {
    /*
        Time Complexity - O(logN)
        Space Complexity - O(1)
        
        Approach
            Basic binary search
            note  use (left<right)  not left <=right because we don't have exact condition to break in loop
            if mid>right -> move left = mid + 1
            else right = mid (because else means mid < right ....so mid could be smallest so we did right = mid not mid -1)
    */

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;

        while(left<right){
            int mid = left + (right - left)/2;

            if(nums[mid]>nums[right]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return nums[left];

    }

 
}