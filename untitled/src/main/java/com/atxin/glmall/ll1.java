package com.atxin.glmall;


import java.util.Arrays;
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
     TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
          this.left = left;
          this.right = right;
      }
  }
class Solution {
    int dp[][][];
    public int findTargetSumWays(int[] nums, int target) {
        dp=new int [nums.length][Math.abs(target)+100][2];
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<Math.abs(target)+100;j++){
                for(int z=0;z<2;z++) Arrays.fill(dp[i][j],-1);
            }
        }
        return dfs(0,target,nums,0,0);
    }
    int dfs(int sum,int target,int nums[],int idx,int state){
        if(idx==nums.length&&sum==target)return 1;
        if(idx==nums.length)return 0;
//        if(Math.abs(sum)>Math.abs(target))return 0;
        if(dp[idx][Math.abs(sum)][state]!=-1)return dp[idx][Math.abs(sum)][state];
         System.out.println(sum+" "+state+" 这是");
        dp[idx][Math.abs(sum)][state]=
                dfs(sum+nums[idx],target,nums,idx+1,sum+nums[idx]>=0?0:1)+
                        dfs(sum-nums[idx],target,nums,idx+1,sum-nums[idx]>=0?0:1);
        System.out.println( dp[idx][Math.abs(sum)][state]);
        return dp[idx][Math.abs(sum)][state];

    }
    int TreeNode(TreeNode root){
        if(root==null)return 0;
TreeNode(root.left.left);
        return 0;
    }
}
public class ll1 {
    public static void main(String[] args) {
        Solution s1=new Solution();
     System.out.println(s1.findTargetSumWays(new int[]{1,1,1,1,1},3)+" "+"答案");
    System.out.println(s1.TreeNode(null));
    }
}
