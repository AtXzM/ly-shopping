package com.atxin.glmall.dp;

import java.util.Arrays;

public class maxContinuitySeq {
    public static void main(String[] args) {
String word1=null;
    }
    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            int dp[]=new int[nums.length+1];
            Arrays.fill(dp,1);
            int ans=1,cnt=1;
            for(int i=1;i<nums.length;i++){
                if(nums[i]>nums[i-1]){
                    cnt++;
                    dp[i]=Math.max(cnt,ans);
                }else{
                    ans=Math.max(ans,cnt);
                    cnt=1;
                    dp[i]=dp[i-1];

                }
            }
            return dp[nums.length-1];
        }
    }
}
