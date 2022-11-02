package com.atxin.glmall;

import java.util.Arrays;
import java.util.Scanner;

public class capsLock {
static int n;
static char charStr[];
static int dp[][];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        String str=sc.next();
        charStr=str.toCharArray();
        dp=new int[str.length()][2];
        for(int i=0;i<str.length();i++) Arrays.fill(dp[i],-1);

        System.out.println(dfs(0,0));
    }

   static int dfs(int idx,int state){
        if(idx==n)return 0;
        if(dp[idx][state]!=-1)return dp[idx][state];
        int ans=0;
        if(state==0){
            if(charStr[idx]>'Z')ans= dfs(idx+1,0)+1;
            else ans= Math.min(dfs(idx+1,0)+2,dfs(idx+1,1)+2);
        }
        else{
            if(charStr[idx]<='Z')ans=dfs(idx+1,1)+1;
            else ans=dfs(idx+1,0)+2;
        }
        dp[idx][state]=ans;
        return ans;
    }
}
