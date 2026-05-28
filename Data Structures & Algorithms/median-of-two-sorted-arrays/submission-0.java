public class Solution {

    /*
        Please don't use O(n+m(log(m+n))) solution
        Time Complexity = O(log(min(m, n)))
        Space Complexity = O(1)

        Approach
            Note - nums1 should always be smaller

        This solution finds the median of two sorted arrays without merging them.
        The main idea is to partition both arrays into left and right halves such
         that every element on the left side is less than or equal to every element on the right side.
        We always binary search on the smaller array, nums1, to keep the search space small. For every partition i in nums1, 
        we calculate partition j in nums2 as j = (m + n + 1) / 2 - i, 
        so the combined left half has the correct number of elements. The +1 helps in odd total length cases by keeping one extra element on the left side.

        For each partition, we check four boundary values:
        nums1_left, nums1_right, nums2_left, and nums2_right. 
        Since i and j are partition positions, left values are at i - 1 and j - 1, 
        while right values are at i and j. 
        Edge cases are handled using Integer.MIN_VALUE and Integer.MAX_VALUE.

        The correct partition condition is nums1_left <= nums2_right && nums2_left <= nums1_right. 
        If this is true, 
            then for odd total length, median is max(nums1_left, nums2_left). 
            For even total length, median is the average of max(left side) and min(right side).

        If nums1_left > nums2_right, we took too many elements from nums1, so we move left using right = i - 1. 
        Otherwise, we took too few elements from nums1, so we move right using left = i + 1.

    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
       
       if(nums1.length>nums2.length){
            return findMedianSortedArrays(nums2,nums1);
       }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0;
        int right = m;

        while(left<=right){
            
            // Note i + j === left half of both array combined
            // So first we take mid of nums1 left
            // Since (m+n+1)/2 is the half of the total elements in the array
            // let say there are 15 total elements 5 in nums1 and  10 in nums2
            // mid of nums1 = 2
            // mid of total element combined is (m+n+1)/2 => (5 + 10 + 1)/2 = 8
            // 2 elements are taken from nums1 ... now we need to take 6 (8 - 2) elements from nums2
            // why we do +1 in m + n + 1 because in case of odd it gives ceil we don't use we 7 in 10 + 5 instead of 8 , but in even case it doesn't change value
            //and also we (m + n + 1)/2 to keep median on left as we are doing Math.max(nums1_left, nums2_left)
            int i = left + (right - left)/2;
            int j = (m + n + 1)/2 - i;
            
            // why Integer.MIN_VALUE as we need max value of comparision
            int nums1_left = (i==0)?Integer.MIN_VALUE:nums1[i-1]; // i - 1 because i is the partition
            int nums1_right = (i==m)?Integer.MAX_VALUE:nums1[i];

            int nums2_left = (j==0)?Integer.MIN_VALUE:nums2[j-1]; // j-1 because j is the partition
            int nums2_right = (j==n)?Integer.MAX_VALUE:nums2[j];

            if(nums1_left<=nums2_right && nums2_left<=nums1_right){
                if((m+n)%2 == 1){
                    return Math.max(nums1_left,nums2_left);
                }

                return (Math.max(nums1_left,nums2_left) + Math.min(nums1_right,nums2_right))/2.0;
            }
            else if(nums1_left>nums2_right){
                //means nums1_left contains too many element lets reduce
                right = i - 1;
            }else{
                left = i + 1;
            }
        }

        return 0.0;


    }
}