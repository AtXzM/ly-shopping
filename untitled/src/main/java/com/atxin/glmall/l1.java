package com.atxin.glmall;

import java.util.Arrays;

//class Solution {
//    int dp[][];
//
//
////    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
////        dp=new int [obstacleGrid.length][obstacleGrid[0].length];
////        for(int i=0;i<dp.length;i++) Arrays.fill(dp[i],-1);
////        return dfs(0,0,obstacleGrid);
////    }
//
////    int dfs(int m,int n,int[][] obstacleGrid){
////        if(m==dp.length-1&&n==dp[0].length)return 1;
////        if(m>=dp.length||n>=dp[0].length)return 0;
////        if(dp[m][n]!=-1)return dp[m][n];
////        int down=0;
////        if(m+1<dp.length&&obstacleGrid[m+1][n]!=1)down=dfs(m+1,n);
////        int right=0;
////        if(n+1<dp[0].length&&obstacleGrid[m][n+1]!=1)right=dfs(m,n+1);
////
////        return dp[m][n]= right+down;
////
////    }
//}
public class l1 {
}
