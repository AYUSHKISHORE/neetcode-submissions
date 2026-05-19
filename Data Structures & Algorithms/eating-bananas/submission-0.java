class Solution {


    /*
        Time Complexity - O(NLogM)
        N = nos of piles
        M = max value in piles

        Approach 
            * We know koko don't need to eat more Max(bananas) inorder to finish with <=h, so we found max element

            * Now instead of traversing from 0 to max element, we do binary search based traversal
            * We find the mid element check whether at mid speed are koko able to eat the bananas
                for(int pile : piles){
                     hours += Math.ceil(double)pile/(double)mid)
                }

                if yes i.e hours<=h{
                    right = mid (instead of mid - 1)  as mid a valid answer
                }else{
                    left = mid + 1 (as mid is not a valid answer)
                }
           
           
            * NOTE (1) -
                    - IN REGULAR BINARY SEARCH we do while(left<=right) -> BECAUSE we have a condition to break and we need exact element
                    - IN THIS BINARY SEARCH we do while(left<right) -> BECAUSE we need to stop when left=right=mid

            * NOTE (2) - 
                if(hours<=h){
                    right = mid;
                }else{
                    left = mid+1;
                }

                we are doing right = mid because mid is valid answer and
                why left = mid + 1 because mid not the valid answer


            * NOTE (3)
                if we not do this 
                     hours += Math.ceil((double)pile/(double)mid);

                     we might get floor values
                     hours += Math.ceil(pile/mid);
                     7/2 = 3 
                     double(7)/(double)2 = 4


                



    */
    public int minEatingSpeed(int[] piles, int h) {
        
        int left = 0;
        int right = Integer.MIN_VALUE;
        for(int pile : piles){
            right = Math.max(right,pile);
        }

        while(left<right){
            int mid = left + (right - left) / 2;

            int hours = 0;
            for(int pile : piles){
                hours += Math.ceil((double)pile/(double)mid);
            }

            if(hours<=h){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;

    }
}