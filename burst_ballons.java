// Time Complexity : O(n^3), where n is the number of ballons in the array
// Space Complexity :O(n^2), where n is the number of ballons in the array (space for dp matrix of size(n*n))
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :

//Three liner explanation of your code in plain english
//Intuition -> //1. you try greedy, which will not work. 
                //2. Then you try to explore all the possible combinations of bursting a ballon. At each step you have 2 options, 
                        //either to burst the ballon or not. (exponential time complexity)
                //3. You Identify the repeating subproblems, and move ahead to try DP (think bottom-up dp always). That is starting 
                        //from 1 subproblem then moving up towards the bigger problem (image the tree upside down)
//1. create a dp matrix of size n*n (though we have only 1 constraint, still we need a 2d matrix as we are finding the permutation 
        //for each case) --> (In DP solution we exactly follow the brute-force recurssive solution)
//2. create all possible ballon windows of sizes starting from 1 to n. And at each window compute the cost of bursting all the ballons
        //in the window and update the dp matrix with the maximum value of each permutation in the window at the respective index.
//3. return the value at dp[0][n-1] (As this contains the maximum possible value of bursting all the ballons)

// Your code here along with comments explaining your approach

class Solution {
    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        //number of ballons to be considered (window size)
        for(int len=1; len<=nums.length; len++){
            //start index of each ballon window under consideration
            for(int start=0; start<=nums.length-len; start++){
                //end index of current ballon window
                int end = start+len-1;
                //iterate over the current ballon window
                for(int i=start; i<=end; i++){
                    //variables to calculate the total cost a ballon burst
                    int leftVal = 1;
                    int rightVal = 1;
                    //variables to get the cost of previous ballon burst from the dp matrix
                    int before = 0;
                    int after = 0;
                    //assign values to the above variables
                    if(start != 0){
                        leftVal = nums[start-1];
                    }
                    if(end != nums.length-1){
                        rightVal = nums[end+1];
                    }
                    if(start != i){
                        before = dp[start][i-1];
                    }
                    if(end != i){
                        after = dp[i+1][end];
                    }
                    //the total value of the cuurent ballon window
                    dp[start][end] = Math.max(dp[start][end], before+leftVal*nums[i]*rightVal + after);
                }
            }
        }
        //max possible value of bursting all ballons
        return dp[0][nums.length-1];
    }
}