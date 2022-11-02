package com.atxin.glmall;

import java.util.Arrays;
import java.util.Scanner;

public class evenSubsquene {
    static int t,n,k;
static int dp[][][];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        t=sc.nextInt();
        while(t-->0){
            n=sc.nextInt();
            k=sc.nextInt();
            int []array=new int[n];
            dp=new int[n][k][2];
            for(int i=0;i<n;i++)
                for(int j=0;j<k;j++)
                    Arrays.fill(dp[i][j],-1);
            for(int i=0;i<n;i++)array[i]=sc.nextInt();

            System.out.println(dfs(0,0,0,array));
        }
    }
    static int dfs(int idx,int currlength,int isEven,int []array){
        if(currlength==k&&isEven%2==0)return 1;
        if(currlength==k)return 0;
        if(idx==n)return 0;
        if(dp[idx][currlength][isEven]!=-1)return dp[idx][currlength][isEven];
        return dp[idx][currlength][isEven]= dfs(idx+1,currlength+1,(isEven+array[idx])%2,array)+
                dfs(idx+1,currlength,isEven,array);
    }

}
