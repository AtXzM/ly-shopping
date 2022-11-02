package com.atxin.glmall.dp;

import java.util.Arrays;

public class binary {
    public static void main(String[] args) {
        Solution s1=new Solution();
        s1.canPartition(new int[]{2,2,1,1});
    }
  static  class Solution {
        int dp[][];
        int sum1=0,sum2=0;
        public boolean canPartition(int[] nums) {
            dp=new int[nums.length+1][2];
            for(int i=0;i<nums.length;i++){
                Arrays.fill(dp[i],-1);
            }
            return dfs(0,0,nums);

        }
        boolean dfs(int idx,int state,int nums[]){
            boolean flag=false;

            if(idx==nums.length){
                System.out.println(sum1+" "+sum2);
                if(sum1==sum2)flag=true;
            }
//            else if(dp[idx][state]!=-1)return dp[idx][state]==0?false:true;
            else{
                sum1+=nums[idx];
                if(dfs(idx+1,0,nums)) flag=true;
                sum1-=nums[idx];

                sum2+=nums[idx];
                if(dfs(idx+1,1,nums))  flag=true;
                sum2-=nums[idx];
            }
            dp[idx][state]=flag?1:0;
            return flag;
        }
    }
}
