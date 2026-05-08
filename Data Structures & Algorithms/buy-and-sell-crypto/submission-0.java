class Solution {
    /*
        Time complexity - O(n)
        Space complexity - O(1)

        Approach 
            * Initialize variable MaxProfit and minBuy as firstIndexValue
            * loop across the prices
                * at each level compute profit
                * check update minBuy if currentIndexValue is less than minBuy

    */

    public int maxProfit(int[] prices) {
        int maxProfit = Integer.MIN_VALUE;
        int minBuy = prices[0];

        for(int price : prices){
            maxProfit = Math.max(maxProfit , price - minBuy);
            minBuy = Math.min(minBuy, price);
        }
        return maxProfit;
    }
}