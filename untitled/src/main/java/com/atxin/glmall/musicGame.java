package com.atxin.glmall;

import java.util.Arrays;
import java.util.Scanner;

public class musicGame {
  static  int dp[][];
  static  int bomb[];
  static int time,n;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        time=sc.nextInt();
        bomb=new int[time];
        dp=new int[n][time];
        for(int i=0;i<time;i++)bomb[i]=sc.nextInt();

        for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);
System.out.println(dfs(0,0));
    }
    static int dfs(int idx,int tim){
        if(tim==time)return 0;
        if(dp[idx][tim]!=-1)return dp[idx][tim];
        int ans=100000000;

        if(bomb[tim]-1!=idx)return dp[idx][tim]=dfs(idx,tim+1);
        else {
            for (int i = 0; i < n; i++) if (i != idx) ans = Math.min(ans, dfs(i, tim + 1) + 1);
        }
        dp[idx][tim]=ans;
        return ans;
    }
}
